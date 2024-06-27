package campus.tech.kakao.contacts

import android.os.Bundle
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
    }
}