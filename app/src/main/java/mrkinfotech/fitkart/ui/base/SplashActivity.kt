package mrkinfotech.fitkart.ui.base

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.ui.home.HomeMainActivity
import mrkinfotech.fitkart.ui.login.LoginActivity
import mrkinfotech.fitkart.utils.PreferenceHelper

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            // 1. Check if Onboarding was shown
            if (!PreferenceHelper.getOnBoarding(this)) {
                startActivity(Intent(this, OnBoardingActivity::class.java))
            }
            // 2. Check if User is Logged In (Now fixed)
            else if (PreferenceHelper.isUserLoggedIn(this)) {
                startActivity(Intent(this, HomeMainActivity::class.java))
            }
            // 3. Otherwise go to Login
            else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 2500)
    }
}