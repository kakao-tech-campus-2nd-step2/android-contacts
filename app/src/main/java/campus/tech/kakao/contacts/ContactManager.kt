package campus.tech.kakao.contacts

import android.content.Context
import android.widget.Toast

class ContactManager() {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun checkIsFilled(text: String): Boolean {
        return text != ""
    }
}