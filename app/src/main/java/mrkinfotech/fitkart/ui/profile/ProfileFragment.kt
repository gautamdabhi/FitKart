package mrkinfotech.fitkart.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import mrkinfotech.fitkart.databinding.FragmentProfileBinding
import mrkinfotech.fitkart.utils.PreferenceHelper

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etProfileName.setText(PreferenceHelper.getUserName(requireContext()))
        binding.etProfileEmail.setText(PreferenceHelper.getUserEmail(requireContext()))

        binding.btnSaveChanges.setOnClickListener {
            val name = binding.etProfileName.text.toString()
            val email = binding.etProfileEmail.text.toString()
            PreferenceHelper.saveUserSession(requireContext(), "local", email, name)
            Toast.makeText(requireContext(), "Profile Saved Locally", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}