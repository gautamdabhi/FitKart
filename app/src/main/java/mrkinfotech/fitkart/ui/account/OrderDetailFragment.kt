package mrkinfotech.fitkart.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mrkinfotech.fitkart.databinding.FragmentOrderDetailsBinding

class OrderDetailFragment : Fragment() {

    private var _binding: FragmentOrderDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Unified key: ORDER_ID
        val orderId = arguments?.getString("ORDER_ID") ?: "N/A"
        val status = arguments?.getString("status") ?: "Completed"
        val total = arguments?.getDouble("totalAmt") ?: 0.0

        binding.tvDetailOrderId.text = "Order ID: #$orderId"
        binding.tvDetailStatus.text = "Status: $status"
        binding.tvDetailTotal.text = "Total Amount: ₹$total"

        binding.btnBackToOrders.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}