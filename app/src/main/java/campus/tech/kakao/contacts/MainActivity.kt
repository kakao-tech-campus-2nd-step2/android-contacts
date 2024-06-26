package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name_input: EditText = findViewById(R.id.name_input)
        val phone_number_input: EditText = findViewById(R.id.phone_number_input)
        val email_input: EditText = findViewById(R.id.email_input)
        val birth_date_input: EditText = findViewById(R.id.birth_date_input)
        val gender_input: EditText = findViewById(R.id.gender_input)
        val memo_input: EditText = findViewById(R.id.memo_input)

        val save_button: Button = findViewById(R.id.save_button)
        val cancel_button: Button = findViewById(R.id.cancel_button)
        val see_more_button: LinearLayoutCompat = findViewById(R.id.see_more_button)
        val see_more_input_form: LinearLayoutCompat = findViewById(R.id.see_more_input_form)

        // 초기화
        see_more_input_form.visibility = View.GONE
        birth_date_input.setClickable(false)
        birth_date_input.setFocusable(false)
        gender_input.setClickable(false)
        gender_input.setFocusable(false)

        see_more_button.setOnClickListener{
            see_more_button.visibility = View.GONE
            see_more_input_form.visibility = View.VISIBLE
        }

        birth_date_input.setOnClickListener{
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)
            this.let { it1 ->
                DatePickerDialog(it1, { _, year, month, day ->
                    run {
                        birth_date_input.setText(year.toString() + "." + (month + 1).toString() + "." + day.toString())
                    }
                }, year, month, day)
            }?.show()
        }


        save_button.setOnClickListener {
            if (name_input.text.isNullOrEmpty()) {
                Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if (phone_number_input.text.isNullOrEmpty()) {
                Toast.makeText(this, "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (!phone_number_input.text.all { Character.isDigit(it) }){
                Toast.makeText(this, "전화번호는 숫자로만 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }


    }
}
