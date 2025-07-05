package mrkinfotech.fitkart.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.FragmentLoginBinding
import mrkinfotech.fitkart.ui.home.HomeMainActivity
import mrkinfotech.fitkart.utils.CustomDialog
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import mrkinfotech.fitkart.utils.PreferenceHelper

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.Signup.setOnClickListener {
            findNavController().navigate(R.id.SignUpFragment)
        }

        firebaseAuth = FirebaseAuth.getInstance()


        binding.buttonLogin.setOnClickListener {
        val email = binding.editTextEmail.text.toString()
        val pass = binding.editTextPassword.text.toString()

        if (email.isNotEmpty() && pass.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    startActivity(Intent(requireContext(), HomeMainActivity::class.java))
                    PreferenceHelper.setUserEmail(requireContext(),email)
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

/*Toast.makeText(
requireContext(),
"Enter valid UserName & password",
Toast.LENGTH_SHORT
).show()*/