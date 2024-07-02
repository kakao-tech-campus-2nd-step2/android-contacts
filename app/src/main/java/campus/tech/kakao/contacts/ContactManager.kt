package campus.tech.kakao.contacts

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat.startActivity
import org.w3c.dom.Text


class ContactManager() {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showCancelAlert(activity: Activity, msg: String) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(msg)
        builder.setPositiveButton(R.string.EXIT_BUTTON_TEXT.toString(), DialogInterface.OnClickListener { dialog, which ->
            activity.finish()
        })
        builder.setNegativeButton(R.string.CONTINUE_BUTTON_TEXT.toString(), DialogInterface.OnClickListener { dialog, which ->
            // 계속 작성
        })
        builder.create()
        builder.show()
    }
    
    fun setTextViewText(textView: TextView, text: String, layout: LinearLayoutCompat? = null) {
        if(text.isBlank()) {
            if (layout != null) {
                layout.visibility = View.GONE
            }
        } else {
           textView.text = text
        }
    }

    fun viewText(textView: View, size:Int) {
        if(size==0) {
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }
    }
}