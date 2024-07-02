package campus.tech.kakao.contacts

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat

class AddContactActivity : AppCompatActivity() {

    val contactManager = ContactManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val name = findViewById<EditText>(R.id.name)
        val phoneNumber = findViewById<EditText>(R.id.phoneNumber)
        val email = findViewById<EditText>(R.id.email)
        val birthDay = findViewById<EditText>(R.id.birthDay)
        val gender = findViewById<RadioGroup>(R.id.gender)
        val memo = findViewById<EditText>(R.id.memo)

        val birthDayLayout = findViewById<LinearLayoutCompat>(R.id.birthDayLayout)
        val genderLayout = findViewById<LinearLayoutCompat>(R.id.genderLayout)
        val memoLayout = findViewById<LinearLayoutCompat>(R.id.memoLayout)

        val more = findViewById<LinearLayoutCompat>(R.id.more)
        val save = findViewById<TextView>(R.id.save)
        val cancel = findViewById<TextView>(R.id.cancel)

        more.setOnClickListener {
            birthDayLayout.visibility = View.VISIBLE
            genderLayout.visibility = View.VISIBLE
            memoLayout.visibility = View.VISIBLE
            more.visibility = View.GONE
        }

        cancel.setOnClickListener {
            val nameText = name.text.toString()
            val phoneNumberText = phoneNumber.text.toString()
            val emailText = email.text.toString()
            val birthDayText = birthDay.text.toString()
            val genderText = when (gender.checkedRadioButtonId) {
                R.id.radioMale -> "Male"
                R.id.radioFemale -> "Female"
                else -> ""
            }
            val memoText = memo.text.toString()
            if(nameText.isNotEmpty() || phoneNumberText.isNotEmpty()
                || emailText.isNotEmpty() || birthDayText.isNotEmpty()
                || genderText.isNotEmpty() || memoText.isNotEmpty()
            )
            {contactManager.showCancelAlert(this@AddContactActivity, R.string.EXIT_INFO_ALERT_TEXT.toString())}
            else contactManager.showCancelAlert(this@AddContactActivity, R.string.EXIT_ALERT_TEXT.toString())
        }

        save.setOnClickListener {
            val nameText = name.text.toString()
            val phoneNumberText = phoneNumber.text.toString()
            val emailText = email.text.toString()
            val birthDayText = birthDay.text.toString()
            val genderText = when (gender.checkedRadioButtonId) {
                R.id.radioMale -> "남성"
                R.id.radioFemale -> "여성"
                else -> ""
            }
            val memoText = memo.text.toString()

            if (nameText.isNotBlank() && phoneNumberText.isNotBlank()) {
                intent.putExtra(Constants.EXTRA_NAME_TEXT, nameText)
                intent.putExtra(Constants.EXTRA_EMAIL_TEXT, emailText)
                intent.putExtra(Constants.EXTRA_PHONE_NUMBER_TEXT, phoneNumberText)
                intent.putExtra(Constants.EXTRA_BIRTH_DAY_TEXT, birthDayText)
                intent.putExtra(Constants.EXTRA_GENDER_TEXT, genderText)
                intent.putExtra(Constants.EXTRA_MEMO_TEXT, memoText)
                setResult(RESULT_OK, intent)
                contactManager.showToast(this@AddContactActivity, R.string.CONTACT_SAVED_TEXT.toString())
                finish()
            } else {
                contactManager.showToast(this@AddContactActivity, R.string.CONDITION_NOT_MET_MESSAGE.toString())
            }
        }
    }
}