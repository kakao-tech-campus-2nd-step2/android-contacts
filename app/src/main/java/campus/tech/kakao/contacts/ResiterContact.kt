package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.Configuration
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class ResiterContact : AppCompatActivity() {
    lateinit var nameForm: EditText
    lateinit var telForm: EditText
    lateinit var mailForm: EditText
    lateinit var birthForm: EditText
    lateinit var sexForm: RadioGroup
    lateinit var sexMale: RadioButton
    lateinit var sexFemale: RadioButton
    lateinit var memoForm: EditText
    lateinit var cancelButton: Button
    lateinit var saveButton: Button

    lateinit var name: String
    lateinit var tel: String
    lateinit var mail: String
    lateinit var sex: String
    lateinit var memo: String
    lateinit var birth: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resiter_contact)

        nameForm = findViewById(R.id.name_form)
        telForm = findViewById(R.id.tel_form)
        mailForm = findViewById(R.id.mail_form)
        birthForm = findViewById(R.id.birth_form)
        val calender: Calendar = Calendar.getInstance()
        sexForm = findViewById(R.id.sex_form)
        sexMale = findViewById(R.id.male)
        sexFemale = findViewById(R.id.female)
        memoForm = findViewById(R.id.memo_form)
        cancelButton = findViewById(R.id.cancel_button)
        saveButton = findViewById(R.id.save_button)

        findViewById<TextView>(R.id.detail_button).setOnClickListener{
            findViewById<LinearLayout>(R.id.detail_button_area).visibility = View.INVISIBLE
            findViewById<LinearLayout>(R.id.detail_form_area).visibility = View.VISIBLE
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calender.set(Calendar.YEAR, year)
            calender.set(Calendar.MONTH, month)
            calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myDateFormat = "yyyy.MM.dd"
            val simpleDateFormat = SimpleDateFormat(myDateFormat, Locale.KOREA)
            birthForm.setText(simpleDateFormat.format(calender.time))
        }
        birthForm.setOnClickListener {
            DatePickerDialog(
                this@ResiterContact,
                dateSetListener,
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        saveButton.setOnClickListener {
            name = nameForm.text.toString()
            tel = telForm.text.toString()
            mail = mailForm.text.toString()
            memo = memoForm.text.toString()
            birth = birthForm.text.toString()
            if (sexMale.isChecked) sex = sexMale.text.toString()
            else if (sexFemale.isChecked) sex = sexFemale.text.toString()
            //Log.d("Log", sex)

            if (name.isEmpty())
                Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            if (tel.isEmpty())
                Toast.makeText(this, "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show()

            if (!name.isEmpty() && !tel.isEmpty())
                Toast.makeText(this, "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }

        cancelButton.setOnClickListener {
            Toast.makeText(this, "취소되었습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
