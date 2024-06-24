package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var name_form: EditText
    lateinit var tel_form: EditText
    lateinit var mail_form: EditText
    lateinit var birth_form: EditText
    lateinit var sex_male: RadioButton
    lateinit var sex_female: RadioButton
    lateinit var memo_form: EditText
    lateinit var cancelButton: Button
    lateinit var saveButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name_form = findViewById(R.id.name_form)
        tel_form = findViewById(R.id.tel_form)
        mail_form = findViewById(R.id.mail_form)
        birth_form = findViewById(R.id.birth_form)
        sex_male = findViewById(R.id.male)
        sex_female = findViewById(R.id.female)
        memo_form = findViewById(R.id.memo_form)
        cancelButton = findViewById(R.id.cancel_button)
        saveButton = findViewById(R.id.save_button)
        
        findViewById<TextView>(R.id.detail_button).setOnClickListener{
            it.visibility = View.GONE
            findViewById<LinearLayout>(R.id.detail_form_area).visibility = View.VISIBLE
        }
    }
}
