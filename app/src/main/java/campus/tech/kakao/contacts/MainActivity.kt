package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import android.widget.RadioGroup
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var phoneNumberEditText: EditText
    private lateinit var plusButton: Button
    private lateinit var mailEditText: EditText
    private lateinit var birthdayEditText: EditText
    private lateinit var memoEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.name)
        saveButton = findViewById(R.id.save)
        phoneNumberEditText = findViewById(R.id.phoneNumber)
        plusButton = findViewById(R.id.plusButton)
        mailEditText = findViewById(R.id.mail)
        birthdayEditText = findViewById(R.id.birthday)
        genderRadioGroup = findViewById(R.id.gender)
        memoEditText = findViewById(R.id.memo)
        cancelButton = findViewById(R.id.cancel)

        birthdayEditText.isFocusable = false
        birthdayEditText.isClickable = true

        val dateClickListener = View.OnClickListener { showDatePickerDialog() }
        birthdayEditText.setOnClickListener(dateClickListener)
        birthdayEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showDatePickerDialog()
            }
        }

        plusButton.setOnClickListener {
            if (birthdayEditText.visibility == View.GONE) {
                birthdayEditText.visibility = View.VISIBLE
                genderRadioGroup.visibility = View.VISIBLE
                memoEditText.visibility = View.VISIBLE
                plusButton.visibility = View.GONE
                plusButton.text = "더보기 ▲"
            } else {
                birthdayEditText.visibility = View.GONE
                genderRadioGroup.visibility = View.GONE
                memoEditText.visibility = View.GONE
                plusButton.visibility = View.VISIBLE
                plusButton.text = "더보기 ▼"
            }
        }

        saveButton.setOnClickListener {
            when {
                nameEditText.text.toString().isEmpty() -> {
                    Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_SHORT).show()
                }

                phoneNumberEditText.text.toString().isEmpty() -> {
                    Toast.makeText(this, "전화번호는 필수 값 입니다.", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(this, "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        cancelButton.setOnClickListener {
            nameEditText.text.clear()
            phoneNumberEditText.text.clear()
            mailEditText.text.clear()
            birthdayEditText.text.clear()
            genderRadioGroup.clearCheck()
            memoEditText.text.clear()
            Toast.makeText(this, "취소되었습니다", Toast.LENGTH_SHORT).show()
        }
    }
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                birthdayEditText.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
