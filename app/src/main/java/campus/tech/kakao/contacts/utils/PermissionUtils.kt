package campus.tech.kakao.contacts.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionUtils {
    companion object {
        const val PERMISSION_REQ_CODE = 1023

        fun checkPermission(permission: String, context: Context): Boolean {
            var result: Int

            result = ContextCompat.checkSelfPermission(context, permission)
            return result == PackageManager.PERMISSION_GRANTED
        }

        fun requestPermission(permission: String, activity: Activity) {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), PERMISSION_REQ_CODE)
        }
    }
}