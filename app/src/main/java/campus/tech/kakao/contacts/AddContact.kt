package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*

import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AddContact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_contact)

        val toggleButton: ImageView = findViewById(R.id.toggle)
        val additionalFieldsLayout: LinearLayout = findViewById(R.id.additionalFieldsLayout)
        val birthdayEditText: TextView = findViewById(R.id.birthday)
        val nameEditText: EditText = findViewById(R.id.name)
        val phoneNumEditText: EditText = findViewById(R.id.phoneNum)
        val maleRadioButton: RadioButton = findViewById(R.id.maleRadioButton)
        val femaleRadioButton: RadioButton = findViewById(R.id.femaleRadioButton)
        val saveButton: TextView = findViewById(R.id.saveButton)
        val cancelButton: TextView = findViewById(R.id.cancelButton)
        val toggleLayout: LinearLayout = findViewById(R.id.toggleLayout)

        toggleButton.setOnClickListener {
            toggleLayout.visibility = View.GONE
            additionalFieldsLayout.visibility = View.VISIBLE
        }

        birthdayEditText.setOnClickListener {
            showDatePickerDialog()
        }

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

//            if (!maleRadioButton.isChecked && !femaleRadioButton.isChecked) {
//                Toast.makeText(this, "성별을 선택해 주세요.", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            Toast.makeText(this, "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }

        cancelButton.setOnClickListener {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            val birthday: TextView = findViewById(R.id.birthday)
            birthday.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }
}


