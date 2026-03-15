package mrkinfotech.fitkart.ui.cart

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.FragmentCartBinding
import mrkinfotech.fitkart.models.CommonDataClass
import mrkinfotech.fitkart.ui.adapter.CartAdapter
import mrkinfotech.fitkart.ui.map.MapActivity

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private var currentCartList = ArrayList<CommonDataClass>()

    private val mapLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val address = result.data?.getStringExtra("selected_address") ?: "Default Address"
            placeOrderInFirebase(address)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCartFromFirebase()
    }

    private fun loadCartFromFirebase() {
        val uId = auth.currentUser?.uid ?: return
        db.collection("cart").document(uId).collection("items").get().addOnSuccessListener { docs ->
            if (_binding == null) return@addOnSuccessListener
            currentCartList.clear()
            var total = 0.0
            for (doc in docs) {
                val item = doc.toObject(CommonDataClass::class.java)
                currentCartList.add(item)
                total += item.itemPrice.toDoubleOrNull() ?: 0.0
            }

            if (currentCartList.isEmpty()) {
                binding.rvCartItems.visibility = View.GONE
                binding.tvEmptyCart.visibility = View.VISIBLE
                binding.btnCheckout.isEnabled = false
                binding.tvTotalAmount.text = "₹0.00"
            } else {
                binding.rvCartItems.visibility = View.VISIBLE
                binding.tvEmptyCart.visibility = View.GONE
                binding.rvCartItems.layoutManager = LinearLayoutManager(requireContext())
                binding.rvCartItems.adapter = CartAdapter(requireContext(), currentCartList) { removed ->
                    db.collection("cart").document(uId).collection("items").document(removed.id).delete()
                        .addOnSuccessListener { loadCartFromFirebase() }
                }
                binding.tvTotalAmount.text = "₹${String.format("%.2f", total)}"
                binding.btnCheckout.isEnabled = true
                binding.btnCheckout.setOnClickListener { mapLauncher.launch(Intent(requireContext(), MapActivity::class.java)) }
            }
        }
    }

    private fun placeOrderInFirebase(address: String) {
        val uId = auth.currentUser?.uid ?: return
        val orderId = "ORD${System.currentTimeMillis()}"
        val total = binding.tvTotalAmount.text.toString()

        // Prepare item list string for the database
        val itemDetails = currentCartList.joinToString("\n") { "${it.itemName} - ₹${it.itemPrice}" }

        val orderData = hashMapOf(
            "orderId" to orderId,
            "address" to address,
            "totalAmount" to total,
            "itemsList" to itemDetails, // Added for the Bill screen
            "status" to "Confirmed",
            "timestamp" to com.google.firebase.Timestamp.now(),
            "userId" to uId
        )

        db.collection("orders").document(orderId).set(orderData).addOnSuccessListener {
            // Clear Firestore Cart
            db.collection("cart").document(uId).collection("items").get().addOnSuccessListener { snapshot ->
                for (doc in snapshot) doc.reference.delete()

                // PASS ORDER ID TO SUCCESS SCREEN -> THEN TO BILL
                val bundle = Bundle()
                bundle.putString("ORDER_ID", orderId)
                findNavController().navigate(R.id.OrderSuccessFragment, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}