package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        val cancelButton: Button = findViewById(R.id.cancel)
        val saveButton: Button = findViewById(R.id.save)

        val name: EditText = findViewById(R.id.name)
        val phoneNumber: EditText = findViewById(R.id.phone_number)
        val mail: EditText = findViewById(R.id.mail)

        cancelButton.setOnClickListener {
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
            val returnIntent: Intent = Intent()
            setResult(RESULT_CANCELED, returnIntent)
            finish()
        }

        saveButton.setOnClickListener {
            if (isValidInfo(name, phoneNumber)) {
                Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
                val returnIntent: Intent = Intent()
                setResult(RESULT_OK, returnIntent)
                finish()
            }
        }
    }

    fun isValidInfo(name: EditText, phoneNumber: EditText): Boolean {
        return if (!isValidName(name)) {
            false
        } else if (!isValidNumber(phoneNumber)) {
            false
        } else true
    }

    private fun isValidName(name: EditText): Boolean {
        return if (name.text.toString() == "") {
            Toast.makeText(this, "이름은 반드시 입력해야 합니다", Toast.LENGTH_SHORT).show()
            false
        } else true
    }

    private fun isValidNumber(phoneNumber: EditText): Boolean {
        val v : Regex = Regex("^[0-9]$")
        return if (phoneNumber.text.toString() == "") {
            Toast.makeText(this, "전화번호는 반드시 입력해야 합니다", Toast.LENGTH_SHORT).show()
            false
        } else true
    }
}