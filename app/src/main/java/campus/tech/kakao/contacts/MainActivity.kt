package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat

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

        see_more_button.setOnClickListener{
            see_more_button.visibility = View.GONE
            see_more_input_form.visibility = View.VISIBLE
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
