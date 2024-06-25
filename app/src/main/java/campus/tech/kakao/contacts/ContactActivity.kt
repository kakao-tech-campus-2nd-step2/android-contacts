package campus.tech.kakao.contacts

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val phoneNumEditText = findViewById<EditText>(R.id.phoneNumEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val birthEditText = findViewById<EditText>(R.id.birthdayEditText)
        val moreButton = findViewById<LinearLayout>(R.id.moreButton)
        val memoEditText = findViewById<EditText>(R.id.memoEditText)
        val genderGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        val saveButton = findViewById<TextView>(R.id.saveButton)
        val cancelButton = findViewById<TextView>(R.id.cancelButton)

        moreButton.setOnClickListener {
            memoEditText.visibility = TextView.VISIBLE
            birthEditText.visibility = TextView.VISIBLE
            genderGroup.visibility = TextView.VISIBLE
            moreButton.visibility = TextView.GONE
        }
        }
    }
