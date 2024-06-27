package campus.tech.kakao.contacts

import android.content.Intent
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

        val ContactList = ArrayList<Contact>()

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
        val cancle = findViewById<TextView>(R.id.cancle)

        more.setOnClickListener {
            birthDayLayout.visibility = View.VISIBLE
            genderLayout.visibility = View.VISIBLE
            memoLayout.visibility = View.VISIBLE
            more.visibility = View.GONE
        }

        cancle.setOnClickListener {
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
            if(contactManager.checkIsFilled(nameText) && contactManager.checkIsFilled(phoneNumberText)
                &&contactManager.checkIsFilled(emailText) && contactManager.checkIsFilled(birthDayText)
                &&contactManager.checkIsFilled(genderText) && contactManager.checkIsFilled(memoText))
            {contactManager.showCancelAlert(this@AddContactActivity, "작성 중인 내용이 있습니다. 정말 나가시겠습니까?")}
            else contactManager.showCancelAlert(this@AddContactActivity, "나가시겠습니까?")
        }

        save.setOnClickListener {
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

            if (contactManager.checkIsFilled(nameText) && contactManager.checkIsFilled(phoneNumberText)) {
                val contact = Contact(nameText, phoneNumberText, emailText, birthDayText, genderText, memoText)
                ContactList.add(contact)
                contactManager.showToast(this@AddContactActivity, "저장되었습니다.")
                startActivity(Intent(this@AddContactActivity, MainActivity::class.java))
                finish()
            } else {
                contactManager.showToast(this@AddContactActivity, "이름과 번호 입력은 필수입니다.")
            }
        }
    }
}