package mrkinfotech.fitkart.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mrkinfotech.fitkart.databinding.ActivityOnBoardingBinding
import mrkinfotech.fitkart.ui.login.LoginActivity
import mrkinfotech.fitkart.utils.PreferenceHelper

class OnBoardingActivity : AppCompatActivity() {

private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGetStarted.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            PreferenceHelper.setOnBoarding(this,true)
        }
    }
}
