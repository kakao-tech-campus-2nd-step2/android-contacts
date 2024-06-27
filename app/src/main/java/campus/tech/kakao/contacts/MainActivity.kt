package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInputForm = findViewById<EditText>(R.id.nameInputForm)
        val phoneInputForm = findViewById<EditText>(R.id.phoneInputForm)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        saveButton.setOnClickListener {
            if (nameInputForm.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_SHORT).show()
            }
            else if (phoneInputForm.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "전화 번호는 필수 값 입니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "저장이 완료 되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        cancelButton.setOnClickListener {
            if (nameInputForm.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_SHORT).show()
            }
            else if (phoneInputForm.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "전화 번호는 필수 값 입니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
