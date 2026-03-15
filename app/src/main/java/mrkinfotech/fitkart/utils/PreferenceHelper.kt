package mrkinfotech.fitkart.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    private const val PREF_NAME = "FitKartPrefs"
    private const val IS_LOGGED_IN = "isLoggedIn"
    private const val ONBOARDING_COMPLETE = "onboardingComplete"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private fun getEditor(context: Context): SharedPreferences.Editor {
        return getPrefs(context).edit()
    }

    fun setOnBoarding(context: Context, isComplete: Boolean) {
        getEditor(context).putBoolean(ONBOARDING_COMPLETE, isComplete).apply()
    }

    fun getOnBoarding(context: Context): Boolean {
        return getPrefs(context).getBoolean(ONBOARDING_COMPLETE, false)
    }

    fun saveUserSession(context: Context, uid: String, email: String, name: String) {
        val editor = getEditor(context)
        editor.putString("userId", uid)
        editor.putString("userEmail", email)
        editor.putString("userName", name)
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.apply()
    }

    fun isUserLoggedIn(context: Context): Boolean {
        return getPrefs(context).getBoolean(IS_LOGGED_IN, false)
    }

    fun getUserName(context: Context): String = getPrefs(context).getString("userName", "FitKart User") ?: "FitKart User"
    fun getUserEmail(context: Context): String = getPrefs(context).getString("userEmail", "user@fitkart.com") ?: "user@fitkart.com"

    // FIX: Clear everything including onboarding so we see the Welcome screen again
    fun clearSession(context: Context) {
        val editor = getEditor(context)
        editor.clear() // This wipes ALL flags: login, user details, AND onboarding
        editor.apply()
    }
}