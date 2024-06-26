package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var cancelButton: TextView
    private lateinit var saveButton: TextView
    private lateinit var phoneNumber: PhoneNumber
    private lateinit var moreButton: LinearLayoutCompat
    private lateinit var moreFields: LinearLayoutCompat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        phoneNumber = PhoneNumber(phoneEditText)

        // 토스트 리스너
        cancelButton.setOnClickListener {
            showToast("취소합니다.")
        }

        saveButton.setOnClickListener {
            if (validateInputs()) {
                showToast("저장합니다.")
            }
        }

        // 더보기 리스너
        moreButton.setOnClickListener {
            moreFields.visibility = LinearLayoutCompat.VISIBLE
            moreButton.visibility = LinearLayoutCompat.GONE
        }
    }

    private fun initViews() {
        nameEditText = findViewById(R.id.nameEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        emailEditText = findViewById(R.id.emailEditText)
        cancelButton = findViewById(R.id.cancelButton)
        saveButton = findViewById(R.id.saveButton)
        moreButton = findViewById(R.id.moreButton)
        moreFields = findViewById(R.id.moreFields)

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
            !phoneNumber.isValidPhoneNumber() -> {
                showToast("전화번호 형식이 올바르지 않습니다.")
                false
            }
            else -> true
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
