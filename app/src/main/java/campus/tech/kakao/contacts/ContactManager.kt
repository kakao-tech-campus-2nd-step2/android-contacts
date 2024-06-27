package campus.tech.kakao.contacts

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity


class ContactManager() {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showCancelAlert(activity: Activity, msg: String) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(msg)
        builder.setPositiveButton("작성하기", DialogInterface.OnClickListener { dialog, which ->
            // 계속 작성
        })
        builder.setNegativeButton("나가기", DialogInterface.OnClickListener { dialog, which ->
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        })
        builder.create()
        builder.show()
    }
}