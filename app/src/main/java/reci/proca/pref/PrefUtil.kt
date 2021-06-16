package reci.proca.pref

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

object PrefUtil {
    fun setDarkTheme(darkTheme: String?) {
        val newState = when (darkTheme) {
            "Light" -> AppCompatDelegate.MODE_NIGHT_NO
            "Dark" -> AppCompatDelegate.MODE_NIGHT_YES
            else -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            } else {
                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            }
        }
        AppCompatDelegate.setDefaultNightMode(newState)
    }
}