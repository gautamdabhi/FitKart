package mrkinfotech.fitkart.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.FragmentAccountBinding
import mrkinfotech.fitkart.ui.base.SplashActivity
import mrkinfotech.fitkart.utils.PreferenceHelper

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvUserName.text = PreferenceHelper.getUserName(requireContext())
        binding.tvUserEmail.text = PreferenceHelper.getUserEmail(requireContext())

        binding.btnMyOrders.setOnClickListener {
            findNavController().navigate(R.id.action_AccountFragment_to_OrdersFragment)
        }

        // LOGOUT LOGIC
        binding.btnLogout.setOnClickListener {
            // 1. Firebase Sign out
            FirebaseAuth.getInstance().signOut()

            // 2. Wipes everything in SharedPrefs (including onboarding flag)
            PreferenceHelper.clearSession(requireContext())

            // 3. Restart the app flow from Splash
            val intent = Intent(requireContext(), SplashActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}