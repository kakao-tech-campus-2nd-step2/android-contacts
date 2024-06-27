package campus.tech.kakao.contacts

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
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
            mail = if (mailForm.text.toString().isNotEmpty()) mailForm.text.toString() else null.toString()
            memo = if (memoForm.text.toString().isNotEmpty()) memoForm.text.toString() else null.toString()
            birth = if (birthForm.text.toString().isNotEmpty()) birthForm.text.toString() else null.toString()
            sex = when {
                sexMale.isChecked -> sexMale.text.toString()
                sexFemale.isChecked -> sexFemale.text.toString()
                else -> null.toString()
            }
            //Log.d("Log", sex)

            if (name.isEmpty()) {
                Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (tel.isEmpty()) {
                Toast.makeText(this, "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultIntent = Intent().apply {
                putExtra("name", name)
                putExtra("tel", tel)
                putExtra("mail", mail)
                putExtra("sex", sex)
                putExtra("memo", memo)
                putExtra("birth", birth)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            Toast.makeText(this, "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        cancelButton.setOnClickListener {
            Toast.makeText(this, "취소되었습니다", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
