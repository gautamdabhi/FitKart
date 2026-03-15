package mrkinfotech.fitkart.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mrkinfotech.fitkart.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // The FragmentContainerView in activity_login.xml handles the Nav Graph
    }
}