package mrkinfotech.fitkart.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mrkinfotech.fitkart.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the title passed from AccountFragment via NavGraph arguments
        val title = arguments?.getString("title") ?: "Settings"

        // Update the UI based on the title
        binding.textToolbarTitle.text = title

        // Set placeholder content based on which section was clicked
        binding.textContent.text = when (title) {
            "Terms & Conditions" -> "Welcome to FitKart. By using our app, you agree to..."
            "Customer Support" -> "For support, please email us at support@fitkart.com"
            "About" -> "FitKart Version 1.0.0\nDeveloped by mrkinfotech"
            else -> "Information not available."
        }

        // Back button logic
        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}