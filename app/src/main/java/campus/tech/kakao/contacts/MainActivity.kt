package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val seeMoreButton = findViewById<Button>(R.id.seeMoreButton)
        seeMore(seeMoreButton)
        showToast()
    }

    private fun seeMore(seeMoreButton: Button) {
        seeMoreButton.setOnClickListener {
            val birthdayEdit = findViewById<EditText>(R.id.birthdayEdit)
            val genderEdit = findViewById<EditText>(R.id.genderEdit)
            val memoEdit = findViewById<EditText>(R.id.memoEdit)
            val genderRadio = findViewById<RadioGroup>(R.id.genderRadio)
            birthdayEdit.visibility = EditText.VISIBLE
            genderEdit.visibility = EditText.VISIBLE
            memoEdit.visibility = EditText.VISIBLE
            genderRadio.visibility = RadioGroup.VISIBLE
        }
    }

    private fun showToast() {
        val cancleButton = findViewById<Button>(R.id.cancleButton)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val nameEdit = findViewById<EditText>(R.id.nameEdit)
        val numberEdit = findViewById<EditText>(R.id.numberEdit)
        cancleButton.setOnClickListener {
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
        }

        saveButton.setOnClickListener {
            if (nameEdit.text.isEmpty()) {
                Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_SHORT).show()
            } else if (numberEdit.text.isEmpty()) {
                Toast.makeText(this, "전화번호는 필수 값 입니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
