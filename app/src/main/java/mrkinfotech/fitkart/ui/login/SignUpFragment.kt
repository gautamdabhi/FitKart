package mrkinfotech.fitkart.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import mrkinfotech.fitkart.databinding.FragmentSignUpBinding
import mrkinfotech.fitkart.ui.home.HomeMainActivity
import mrkinfotech.fitkart.utils.CustomDialog
import mrkinfotech.fitkart.utils.PreferenceHelper

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigate back to Login
        binding.tvGoToLogin.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnSignUp.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etSignUpEmail.text.toString().trim()
            val pass = binding.etSignUpPass.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {
                // Show a simple progress indication if you have one, or use a toast
                CustomDialog.showToastMessage(requireContext(), "Creating account...")

                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser

                        // Save session using your PreferenceHelper
                        PreferenceHelper.saveUserSession(
                            requireContext(),
                            user?.uid ?: "",
                            email,
                            name
                        )

                        CustomDialog.showToastMessage(requireContext(), "Account created successfully!")

                        // Move to Home and clear the login stack
                        val intent = Intent(requireContext(), HomeMainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    } else {
                        val error = task.exception?.message ?: "Signup Failed"
                        CustomDialog.showToastMessage(requireContext(), error)
                    }
                }
            } else {
                CustomDialog.showToastMessage(requireContext(), "Please fill all fields")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}