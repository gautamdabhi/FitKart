package mrkinfotech.fitkart.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.FragmentBillBinding

class BillFragment : Fragment() {
    private var _binding: FragmentBillBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        _binding = FragmentBillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderId = arguments?.getString("ORDER_ID")

        if (orderId != null) {
            FirebaseFirestore.getInstance().collection("orders").document(orderId).get()
                .addOnSuccessListener { doc ->
                    if (_binding != null && doc.exists()) {
                        binding.tvBillAddress.text = doc.getString("address") ?: "N/A"
                        binding.tvBillTotal.text = doc.getString("totalAmount") ?: "₹0.00"
                        binding.tvBillItems.text = doc.getString("itemsList") ?: "No items found"
                    }
                }
                .addOnFailureListener {
                    binding.tvBillAddress.text = "Error fetching details"
                }
        }

        binding.btnDone.setOnClickListener {
            findNavController().navigate(R.id.HomeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}