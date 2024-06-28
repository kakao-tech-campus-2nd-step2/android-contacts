package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddContact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_contact)

        val nameEditText: EditText = findViewById(R.id.name)
        val phoneNumEditText: EditText = findViewById(R.id.phoneNum)
        val saveButton: TextView = findViewById(R.id.saveButton)
        val cancelButton: TextView = findViewById(R.id.cancelButton)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val phoneNumber = phoneNumEditText.text.toString()

            if (name.isEmpty() || phoneNumber.isEmpty()) {
                Toast.makeText(this, "이름과 전화번호는 필수 값입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!phoneNumber.matches("\\d+".toRegex())) {
                Toast.makeText(this, "전화번호는 숫자만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val resultIntent = Intent().apply {
                putExtra("name", name)
                putExtra("phoneNum", phoneNumber)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}
