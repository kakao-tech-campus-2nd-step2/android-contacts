package campus.tech.kakao.contacts

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log

import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


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
    private lateinit var contactViewModel: ContactViewModel


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

        contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]


        moreButton.setOnClickListener {
            memoEditText.visibility = TextView.VISIBLE
            birthEditText.visibility = TextView.VISIBLE
            genderRadioGroup.visibility = TextView.VISIBLE
            moreButton.visibility = TextView.GONE
        }

        birthEditText.setOnClickListener {
            showDatePickerDialog()
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val phoneNum = phoneNumEditText.text.toString()
            val email = emailEditText.text.toString()
            val birth = birthEditText.text.toString()
            val gender = getSelectedGender()
            val memo = memoEditText.text.toString()
            val contact = Contact(name, phoneNum, email, birth, gender, memo)

            if (checkTerms(name, phoneNum)) {
                saveContact(contact)
            }

        }


        }
    private fun showDatePickerDialog(){
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            birthEditText.setText("$year-${month + 1}-$dayOfMonth")
        }, 2021, 0, 1)
        datePickerDialog.show()
    }

    private fun checkTerms(name: String, phoneNum: String): Boolean {
        if(name.isEmpty() || phoneNum.isEmpty()){
            Toast.makeText(this, "이름과 전화번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }

    private fun getSelectedGender(): String {
        val selectedId = genderRadioGroup.checkedRadioButtonId
        return if (selectedId != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedId)
            selectedRadioButton.text.toString()
        } else {
            "미정"
        }
    }

    private fun saveContact(contact: Contact) {
        contactViewModel.addContact(contact)
        Toast.makeText(this, "연락처가 저장되었습니다.", Toast.LENGTH_SHORT).show()
        Log.d("testt",contactViewModel.getContact(contact.name).toString() )
    }
}
