package mrkinfotech.fitkart.utils

import android.content.Context
import androidx.core.net.toUri

object AppHelper {

    fun getDrawableUri(context: Context, drawableId: Int): String {
        return "android.resource://${context.packageName}/$drawableId".toUri().toString()
    }
}