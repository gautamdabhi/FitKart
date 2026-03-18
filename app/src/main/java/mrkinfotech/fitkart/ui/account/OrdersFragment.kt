package mrkinfotech.fitkart.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import mrkinfotech.fitkart.databinding.FragmentOrdersBinding
import mrkinfotech.fitkart.ui.adapter.OrderAdapter

class OrdersFragment : Fragment() {
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Back button logic
        binding.ivBackOrders.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            binding.tvNoOrders.visibility = View.VISIBLE
            binding.tvNoOrders.text = "Please login to see orders"
            return
        }

        FirebaseFirestore.getInstance().collection("orders")
            .whereEqualTo("userId", user.uid)
            .get()
            .addOnSuccessListener { docs ->
                if (_binding == null) return@addOnSuccessListener
                val orderList = ArrayList<Map<String, Any>>()
                for (doc in docs) {
                    orderList.add(doc.data)
                }

                if (orderList.isEmpty()) {
                    binding.rvOrders.visibility = View.GONE
                    binding.tvNoOrders.visibility = View.VISIBLE
                } else {
                    binding.tvNoOrders.visibility = View.GONE
                    binding.rvOrders.visibility = View.VISIBLE
                    binding.rvOrders.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvOrders.adapter = OrderAdapter(orderList)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}