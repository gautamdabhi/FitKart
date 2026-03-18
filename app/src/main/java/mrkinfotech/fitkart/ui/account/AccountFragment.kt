package mrkinfotech.fitkart.ui.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.FragmentAccountBinding
import mrkinfotech.fitkart.ui.login.LoginActivity
import mrkinfotech.fitkart.ui.map.MapActivity
import mrkinfotech.fitkart.utils.PreferenceHelper

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    // Register the launcher to handle the result from MapActivity
    private val mapResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val address = result.data?.getStringExtra("selected_address")
            if (address != null) {
                // Update the UI
                binding.textDeliveryAddress.text = address
                // Save to SharedPreferences so it stays updated
                PreferenceHelper.saveProfileData(requireContext(), address)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load existing data
        binding.textUserName.text = PreferenceHelper.getUserName(requireContext())
        binding.textUserEmail.text = PreferenceHelper.getUserEmail(requireContext())

        // Load existing address if it exists
        val savedAddress = PreferenceHelper.getProfileData(requireContext())
        if (savedAddress.isNotEmpty()) {
            binding.textDeliveryAddress.text = savedAddress
        }

        binding.OrdersLayout.setOnClickListener {
            findNavController().navigate(R.id.action_AccountFragment_to_OrdersFragment)
        }

        // 2. DELIVERY ADDRESS: Launch MapActivity
        binding.deliveryAddressLayout.setOnClickListener {
            val intent = Intent(requireContext(), MapActivity::class.java)
            mapResultLauncher.launch(intent)
        }

        binding.termAndConditionLayout.setOnClickListener {
            val bundle = Bundle().apply { putString("title", "Terms & Conditions") }
            findNavController().navigate(R.id.action_AccountFragment_to_SettingsFragment, bundle)
        }

        binding.customerSupportLayout.setOnClickListener {
            val bundle = Bundle().apply { putString("title", "Customer Support") }
            findNavController().navigate(R.id.action_AccountFragment_to_SettingsFragment, bundle)
        }

        binding.aboutLayout.setOnClickListener {
            val bundle = Bundle().apply { putString("title", "About") }
            findNavController().navigate(R.id.action_AccountFragment_to_SettingsFragment, bundle)
        }

        binding.buttonLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            PreferenceHelper.clearSession(requireContext())
            val intent = Intent(requireActivity(), LoginActivity::class.java)
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