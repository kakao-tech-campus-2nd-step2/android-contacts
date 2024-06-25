package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText : EditText
    private lateinit var saveButton : Button
    private lateinit var phoneNumberEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.name)
        saveButton = findViewById(R.id.save)
        phoneNumberEditText = findViewById(R.id.phoneNumber)

        saveButton.setOnClickListener {
            when {
                nameEditText.text.toString().isEmpty() -> {
                    Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_SHORT).show()
                }
                phoneNumberEditText.text.toString().isEmpty() -> {
                    Toast.makeText(this, "전화번호는 필수 값 입니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
