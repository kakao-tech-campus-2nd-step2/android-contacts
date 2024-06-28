package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    lateinit var name: TextView
    lateinit var phoneNumber: TextView
    lateinit var mail: TextView
    lateinit var birthday: TextView
    lateinit var gender: TextView
    lateinit var memo: TextView
    lateinit var nameInputField: TextView
    lateinit var phoneNumberInputField: TextView
    lateinit var mailInputField: TextView
    lateinit var birthdayInputField: TextView
    lateinit var genderInputField: TextView
    lateinit var memoInputField: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        name = findViewById(R.id.name)
        phoneNumber = findViewById(R.id.phone_number)
        mail = findViewById(R.id.mail)
        birthday = findViewById(R.id.birthday)
        gender = findViewById(R.id.gender)
        memo = findViewById(R.id.memo)
        nameInputField = findViewById(R.id.input_name_field)
        phoneNumberInputField = findViewById(R.id.input_phone_number_field)
        mailInputField = findViewById(R.id.input_mail_field)
        birthdayInputField = findViewById(R.id.input_birthday_field)
        genderInputField = findViewById(R.id.input_gender_field)
        memoInputField = findViewById(R.id.input_memo_field)

        val intent = intent
        nameInputField.setText(intent.getStringExtra("name"))
        phoneNumberInputField.setText(intent.getStringExtra("phoneNumber"))
        mailInputField.setText(intent.getStringExtra("mail"))
        birthdayInputField.setText(intent.getStringExtra("birthday"))
        genderInputField.setText(intent.getStringExtra("gender"))
        memoInputField.setText(intent.getStringExtra("memo"))
        setVisibleCompletedFields()
    }

    fun setVisibleCompletedFields() {
        if(ExistMail()){
            mail.visibility = View.VISIBLE
            mailInputField.visibility = View.VISIBLE
        }
        if(ExistBirthday()){
            birthday.visibility = View.VISIBLE
            birthdayInputField.visibility = View.VISIBLE
        }
        if(ExistGender()){
            gender.visibility = View.VISIBLE
            genderInputField.visibility = View.VISIBLE
        }
        if(ExistMemo()){
            memo.visibility = View.VISIBLE
            memoInputField.visibility = View.VISIBLE
        }

    }

    fun ExistMail(): Boolean {
        return mailInputField.text.toString() != ""
    }

    fun ExistBirthday(): Boolean {
        return birthdayInputField.text.toString() != ""
    }

    fun ExistGender(): Boolean {
        return genderInputField.text.toString() != ""
    }

    fun ExistMemo(): Boolean {
        return memoInputField.text.toString() != ""
    }
}