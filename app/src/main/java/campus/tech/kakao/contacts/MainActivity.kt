package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
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
        val moreButton = findViewById<LinearLayout>(R.id.moreButton)
        val moreInformationForm = findViewById<LinearLayout>(R.id.moreInformationForm)
        val birthdayInputForm = findViewById<TextView>(R.id.birthdayInputForm)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)


        moreButton.setOnClickListener {
            moreInformationForm.setVisibility(View.VISIBLE)
            moreButton.setVisibility(View.GONE)
        }

        birthdayInputForm.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this,
                { _, year, monthOfYear, dayOfMonth ->
                    val formattedMonth = String.format("%02d", monthOfYear + 1)
                    val formattedDay = String.format("%02d", dayOfMonth)
                    birthdayInputForm.text = "$year.$formattedMonth.$formattedDay"
                }, 2000, 0, 1)
            datePickerDialog.show()
        }

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
            else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
