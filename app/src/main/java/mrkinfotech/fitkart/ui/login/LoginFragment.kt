package mrkinfotech.fitkart.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.FragmentLoginBinding
import mrkinfotech.fitkart.ui.home.HomeMainActivity
import mrkinfotech.fitkart.utils.AppConstant
import mrkinfotech.fitkart.utils.CustomDialog
import mrkinfotech.fitkart.utils.PreferenceHelper

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var googleSignInClient: GoogleSignInClient
    private val auth = FirebaseAuth.getInstance()
    private val RC_SIGN_IN = 9001

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(AppConstant.WEB_CLIENT_ID)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        // 1. CONTINUE WITH GOOGLE (ID: buttonContinueWithGoogle)
        binding.buttonContinueWithGoogle.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            googleSignInClient.signOut().addOnCompleteListener {
                val signInIntent = googleSignInClient.signInIntent
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
        }

        // 2. EMAIL LOGIN (ID: btnLogin)
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                binding.progressBar.visibility = View.VISIBLE
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    binding.progressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        startActivity(Intent(requireContext(), HomeMainActivity::class.java))
                        requireActivity().finish()
                    } else {
                        CustomDialog.showToastMessage(requireContext(), "Login Failed: ${task.exception?.message}")
                    }
                }
            } else {
                CustomDialog.showToastMessage(requireContext(), "Please fill all fields")
            }
        }

        // 3. GO TO SIGN UP (ID: tvGoToSignUp)
        binding.tvGoToSignUp.setOnClickListener {
            // This navigates to the SignUpFragment ID in your nav_graph
            findNavController().navigate(R.id.SignUpFragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                binding.progressBar.visibility = View.GONE
                CustomDialog.showToastMessage(requireContext(), "Sign-In Canceled")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            binding.progressBar.visibility = View.GONE
            if (task.isSuccessful) {
                val user = auth.currentUser
                PreferenceHelper.saveUserSession(requireContext(), user?.uid ?: "", user?.email ?: "", user?.displayName ?: "FitKart User")
                startActivity(Intent(requireContext(), HomeMainActivity::class.java))
                requireActivity().finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}