package mrkinfotech.fitkart.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.ActivityMainBinding

class HomeMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        // FIX: Check if we need to redirect to Cart immediately
        if (intent.getBooleanExtra("SHOW_CART", false)) {
            binding.bottomNavigationView.selectedItemId = R.id.CartFragment
        }
    }

    // Handles redirection if the activity is already open
    override fun onNewIntent(intent: android.content.Intent) {
        super.onNewIntent(intent)
        if (intent.getBooleanExtra("SHOW_CART", false)) {
            binding.bottomNavigationView.selectedItemId = R.id.CartFragment
        }
    }
}