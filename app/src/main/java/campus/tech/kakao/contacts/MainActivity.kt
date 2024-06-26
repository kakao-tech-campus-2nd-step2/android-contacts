package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var cancelButton: TextView
    private lateinit var saveButton: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        // 토스트 리스너
        cancelButton.setOnClickListener {
            showToast("취소합니다.")
        }

        saveButton.setOnClickListener {
            if (validateInputs()) {
                showToast("저장합니다.")
            }
        }
    }

    private fun initViews() {
        nameEditText = findViewById(R.id.nameEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        emailEditText = findViewById(R.id.emailEditText)
        cancelButton = findViewById(R.id.cancelButton)
        saveButton = findViewById(R.id.saveButton)
    }

    // 기본 저장 설정을 추가할 수 있다.
    private val requiredFields = listOf(
        Pair("이름") { nameEditText.text.isNullOrEmpty() },
        Pair("전화번호") { phoneEditText.text.isNullOrEmpty() }
    )

    private fun validateInputs(): Boolean {
        val missingFields = requiredFields.filter { it.second() }.map { it.first }

        return when {
            missingFields.isNotEmpty() -> {
                showToast("${missingFields.joinToString(", ")}을(를) 입력하세요.")
                false
            }
            else -> true
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
