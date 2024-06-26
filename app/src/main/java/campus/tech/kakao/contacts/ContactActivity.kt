package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val cancelButton: Button = findViewById(R.id.cancel)
        val saveButton: Button = findViewById(R.id.save)

        val name: EditText = findViewById(R.id.name)
        val phoneNumber: EditText = findViewById(R.id.phone_number)
        val mail: EditText = findViewById(R.id.mail)

        val viewMore: ConstraintLayout = findViewById(R.id.view_more)

        val birth: TextView = findViewById(R.id.birthday)
        val sex: RadioGroup = findViewById(R.id.sex_radio_group)
        val memo: EditText = findViewById(R.id.memo)

        cancelButton.setOnClickListener {
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
            val returnIntent: Intent = Intent()
            setResult(RESULT_CANCELED, returnIntent)
            finish()
        }

        saveButton.setOnClickListener {
            if (isValidInfo(name, phoneNumber)) {
                saveInfo(name, phoneNumber, mail, birth, sex, memo)
                Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
                val returnIntent: Intent = Intent()
                setResult(RESULT_OK, returnIntent)
                finish()
            }
        }

        viewMore.setOnClickListener {
            findViewById<ConstraintLayout>(R.id.more_infos).visibility = ConstraintLayout.VISIBLE
            viewMore.visibility = Button.GONE
        }

        birth.setOnClickListener {
            val calendar = Calendar.getInstance()
            val cYear = calendar.get(Calendar.YEAR)
            val cMonth = calendar.get(Calendar.MONTH)
            val cDay = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this,
                { _, year, month, dayOfMonth -> birth.text = "$year-%02d-%02d".format(month + 1, dayOfMonth) },
                cYear, cMonth, cDay).show()
        }
    }

    fun isValidInfo(name: EditText, phoneNumber: EditText): Boolean {
        return if (!isValidName(name)) {
            false
        } else if (!isValidNumber(phoneNumber)) {
            false
        } else true
    }

    private fun isValidName(name: EditText): Boolean {
        return if (name.text.toString() == "") {
            Toast.makeText(this, "이름은 반드시 입력해야 합니다", Toast.LENGTH_SHORT).show()
            false
        } else true
    }

    private fun isValidNumber(phoneNumber: EditText): Boolean {
        val v : Regex = Regex("^[0-9]+$")
        return if (v.matches(phoneNumber.text.toString())) {
            true
        } else if (phoneNumber.text.toString() == "") {
            Toast.makeText(this, "전화번호는 반드시 입력해야 합니다", Toast.LENGTH_SHORT).show()
            false
        } else {
            Toast.makeText(this, "전화번호는 숫자로만 구성되어야 합니다", Toast.LENGTH_SHORT).show()
            false
        }
    }

    fun saveInfo(name: EditText, phoneNumber: EditText, mail: EditText,
                 birth: TextView, sex: RadioGroup, memo: EditText): Contact {
        val nameInfo: String = name.text.toString()
        val phoneNumberInfo: String = phoneNumber.text.toString()
        val mailInfo: String = mail.text.toString()
        val birthInfo: String = birth.text.toString()
        val sexInfo: Int = sex.checkedRadioButtonId
        val memoInfo: String = memo.text.toString()

        val contact: Contact = Contact(nameInfo, phoneNumberInfo,mailInfo, birthInfo, sexInfo, memoInfo)
        return contact
    }
}