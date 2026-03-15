package mrkinfotech.fitkart.ui.cart

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.FragmentOrderSuccessBinding

class OrderSuccessFragment : Fragment() {
    private var _binding: FragmentOrderSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        _binding = FragmentOrderSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderId = arguments?.getString("ORDER_ID")

        Handler(Looper.getMainLooper()).postDelayed({
            if (_binding != null) {
                val bundle = Bundle()
                bundle.putString("ORDER_ID", orderId)
                findNavController().navigate(R.id.BillFragment, bundle)
            }
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}