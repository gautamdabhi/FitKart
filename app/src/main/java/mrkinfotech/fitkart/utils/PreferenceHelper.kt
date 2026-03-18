package mrkinfotech.fitkart.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private const val PREF_NAME = "FitKartPrefs"
    private const val IS_USER_LOGGED_IN = "isUserLoggedIn"
    private const val KEY_USER_ID = "userId"
    private const val KEY_USER_EMAIL = "userEmail"
    private const val KEY_USER_NAME = "userName"
    private const val KEY_ONBOARDING_COMPLETE = "onboardingComplete"
    private const val KEY_USER_ADDRESS = "userAddress" // New key for address

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // --- EXISTING SESSION LOGIC (Do not change) ---

    fun saveUserSession(context: Context, userId: String, email: String, name: String) {
        val editor = getPreferences(context).edit()
        editor.putBoolean(IS_USER_LOGGED_IN, true)
        editor.putString(KEY_USER_ID, userId)
        editor.putString(KEY_USER_EMAIL, email)
        editor.putString(KEY_USER_NAME, name)
        editor.apply()
    }

    fun isUserLoggedIn(context: Context): Boolean {
        return getPreferences(context).getBoolean(IS_USER_LOGGED_IN, false)
    }

    fun getUserName(context: Context): String {
        return getPreferences(context).getString(KEY_USER_NAME, "User") ?: "User"
    }

    fun getUserEmail(context: Context): String {
        return getPreferences(context).getString(KEY_USER_EMAIL, "") ?: ""
    }

    fun setOnBoarding(context: Context, isComplete: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_ONBOARDING_COMPLETE, isComplete).apply()
    }

    fun getOnBoarding(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_ONBOARDING_COMPLETE, false)
    }

    fun clearSession(context: Context) {
        getPreferences(context).edit().clear().apply()
    }

    // --- NEW ADDRESS METHODS (Fixes line 31 error) ---

    /**
     * Saves the address string returned from MapActivity
     */
    fun saveProfileData(context: Context, address: String) {
        getPreferences(context).edit().putString(KEY_USER_ADDRESS, address).apply()
    }

    /**
     * Retrieves the saved address string
     */
    fun getProfileData(context: Context): String {
        return getPreferences(context).getString(KEY_USER_ADDRESS, "") ?: ""
    }
}