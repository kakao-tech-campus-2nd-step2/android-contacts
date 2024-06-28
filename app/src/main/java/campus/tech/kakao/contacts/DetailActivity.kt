package campus.tech.kakao.contacts

import android.opengl.Visibility
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.VisibleActivityCallback
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_layout)

        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        val birthday = intent.getStringExtra("birthday")
        var gender = intent.getStringExtra("gender")
        val memo = intent.getStringExtra("memo")

        val namePrint = findViewById<LinearLayout>(R.id.namePrint)
        val phonePrint = findViewById<LinearLayout>(R.id.phonePrint)
        val emailPrint = findViewById<LinearLayout>(R.id.emailPrint)
        val birthdayPrint = findViewById<LinearLayout>(R.id.birthdayPrint)
        val genderPrint = findViewById<LinearLayout>(R.id.genderPrint)
        val memoPrint = findViewById<LinearLayout>(R.id.memoPrint)

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val phoneTextView = findViewById<TextView>(R.id.phoneTextView)
        val emailTextView = findViewById<TextView>(R.id.emailTextView)
        val birthdayTextView = findViewById<TextView>(R.id.birthdayTextView)
        val genderTextView = findViewById<TextView>(R.id.genderTextView)
        val memoTextView = findViewById<TextView>(R.id.memoTextView)

        nameTextView.text = name
        phoneTextView.text = phone
        emailTextView.text = email
        birthdayTextView.text = birthday
        genderTextView.text = gender
        memoTextView.text = memo

        if (emailTextView.text == "") {
            emailPrint.setVisibility(View.GONE)
        }
        if (birthdayTextView.text == "") {
            birthdayPrint.setVisibility(View.GONE)
        }
        if (genderTextView.text == "-1") {
            genderPrint.setVisibility(View.GONE)
        }
        if (memoTextView.text == "") {
            memoPrint.setVisibility(View.GONE)
        }
    }
}