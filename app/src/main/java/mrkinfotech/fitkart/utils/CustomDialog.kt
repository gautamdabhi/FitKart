package mrkinfotech.fitkart.utils

import android.content.Context
import android.widget.Toast


object CustomDialog {
    fun showToastMessage(context: Context,message: String) {
        Toast.makeText(
            context, message,
            Toast.LENGTH_SHORT
        ).show()
    }
}