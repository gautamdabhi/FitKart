package mrkinfotech.fitkart.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import mrkinfotech.fitkart.databinding.FragmentSignUpBinding
import mrkinfotech.fitkart.utils.CustomDialog

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Updated to match ids.xml: tvSignUpTitle
        binding.tvSignUpTitle.setOnClickListener {
            findNavController().navigateUp()
        }

        // Updated to match ids.xml: btnSignUp
        binding.btnSignUp.setOnClickListener {
            // Using IDs from your layout: etSignUpEmail and etSignUpPass
            val email = binding.etSignUpEmail.text.toString().trim()
            val pass = binding.etSignUpPass.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        CustomDialog.showToastMessage(requireContext(), "Account created successfully!")
                        findNavController().navigateUp()
                    } else {
                        CustomDialog.showToastMessage(requireContext(), task.exception?.message ?: "Signup Failed")
                    }
                }
            } else {
                CustomDialog.showToastMessage(requireContext(), "Please fill all fields")
            }
        }
    }
}