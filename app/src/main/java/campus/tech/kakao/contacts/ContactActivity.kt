package campus.tech.kakao.contacts

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ContactActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneNumEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var birthEditText: EditText
    private lateinit var memoEditText: EditText
    private lateinit var saveButton: TextView
    private lateinit var cancelButton: TextView
    private lateinit var moreButton: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        nameEditText = findViewById<EditText>(R.id.nameEditText)
        phoneNumEditText = findViewById<EditText>(R.id.phoneNumEditText)
        emailEditText = findViewById<EditText>(R.id.emailEditText)
        birthEditText = findViewById<EditText>(R.id.birthdayEditText)
        moreButton = findViewById<LinearLayout>(R.id.moreButton)
        memoEditText = findViewById<EditText>(R.id.memoEditText)
        genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        saveButton = findViewById<TextView>(R.id.saveButton)
        cancelButton = findViewById<TextView>(R.id.cancelButton)

        moreButton.setOnClickListener {
            memoEditText.visibility = TextView.VISIBLE
            birthEditText.visibility = TextView.VISIBLE
            genderRadioGroup.visibility = TextView.VISIBLE
            moreButton.visibility = TextView.GONE
        }

        birthEditText.setOnClickListener {
            showDatePickerDialog()
        }


        }
    private fun showDatePickerDialog(){
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            birthEditText.setText("$year-${month + 1}-$dayOfMonth")
        }, 2021, 0, 1)
        datePickerDialog.show()
    }

}
