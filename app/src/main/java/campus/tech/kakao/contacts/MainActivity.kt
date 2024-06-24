package campus.tech.kakao.contacts

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

class MainActivity : AppCompatActivity() {
    lateinit var name_form: EditText
    lateinit var tel_form: EditText
    lateinit var mail_form: EditText
    lateinit var birth_form: EditText
    lateinit var sex_form: RadioGroup
    lateinit var sex_male: RadioButton
    lateinit var sex_female: RadioButton
    lateinit var memo_form: EditText
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
        setContentView(R.layout.activity_main)

        name_form = findViewById(R.id.name_form)
        tel_form = findViewById(R.id.tel_form)
        mail_form = findViewById(R.id.mail_form)
        birth_form = findViewById(R.id.birth_form)
        sex_form = findViewById(R.id.sex_form)
        sex_male = findViewById(R.id.male)
        sex_female = findViewById(R.id.female)
        memo_form = findViewById(R.id.memo_form)
        cancelButton = findViewById(R.id.cancel_button)
        saveButton = findViewById(R.id.save_button)

        findViewById<TextView>(R.id.detail_button).setOnClickListener{
            it.visibility = View.GONE
            findViewById<LinearLayout>(R.id.detail_form_area).visibility = View.VISIBLE
        }

        saveButton.setOnClickListener {
            name = name_form.text.toString()
            tel = tel_form.text.toString()
            mail = mail_form.text.toString()
            memo = memo_form.text.toString()
            birth = birth_form.text.toString()
            if (sex_male.isChecked) sex = sex_male.text.toString()
            else if (sex_female.isChecked) sex = sex_female.text.toString()
            //Log.d("Log", sex)

            if (name.isEmpty())
                Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            if (tel.isEmpty())
                Toast.makeText(this, "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show()

        }
    }
}
