package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SpecificInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.specific_information)
        val textViewName = findViewById<TextView>(R.id.textViewName)
        val textViewPhoneNumber = findViewById<TextView>(R.id.textViewPhoneNumber)
        val textViewEmail = findViewById<TextView>(R.id.textViewEmail)
        val textViewBirth = findViewById<TextView>(R.id.textViewBirth)
        val textViewGender = findViewById<TextView>(R.id.textViewGender)
        val textViewMemo = findViewById<TextView>(R.id.textViewMemo)
        val name = intent.getStringExtra("name") ?: ""
        val birth = intent.getStringExtra("birth") ?: ""
        val memo = intent.getStringExtra("memo") ?: ""
        val phoneNumber = intent.getStringExtra("phoneNumber") ?: ""
        val gender = intent.getStringExtra("gender") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        setTextViewIfNotNullOrEmpty(textViewName, name)
        setTextViewIfNotNullOrEmpty(textViewPhoneNumber, phoneNumber)
        setTextViewIfNotNullOrEmpty(textViewEmail, email)
        setTextViewIfNotNullOrEmpty(textViewBirth, birth)
        setTextViewIfNotNullOrEmpty(textViewGender, gender)
        setTextViewIfNotNullOrEmpty(textViewMemo, memo)
    }
    private fun setTextViewIfNotNullOrEmpty(textView: TextView, text: String) {
        if (text.isNotEmpty()) {
            val fieldName = when (textView.id) {
                R.id.textViewName -> "Name"
                R.id.textViewPhoneNumber -> "Phone Number"
                R.id.textViewEmail -> "Email"
                R.id.textViewBirth -> "Birth"
                R.id.textViewGender -> "Gender"
                R.id.textViewMemo -> "Memo"
                else -> ""
            }
            textView.text = " ${fieldName} : ${text}"
            textView.visibility = View.VISIBLE
            if(fieldName == "Birth" && text == "생일"){
                textView.visibility = View.GONE
            }
            if(fieldName == "Gender" && text == "null"){
                textView.visibility = View.GONE
            }
        } else {
            textView.visibility = View.GONE
        }
    }


}
