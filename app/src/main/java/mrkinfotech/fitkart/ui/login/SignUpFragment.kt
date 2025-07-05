package mrkinfotech.fitkart.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import mrkinfotech.fitkart.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSignUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.textTitleSignup.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
        binding.buttonSignUp.setOnClickListener {

            val userName = binding.editTextUserName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val pass = binding.editTextPassword.text.toString()

            if (userName.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(requireContext(), LoginActivity::class.java))
                        } else {
                            Toast.makeText(
                                requireContext(),
                                it.exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }


            } else {
                Toast.makeText(requireContext(), "Empty fields are not allowed", Toast.LENGTH_SHORT)
                    .show()

            }
        }
    }

}