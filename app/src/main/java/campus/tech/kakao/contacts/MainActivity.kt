package campus.tech.kakao.contacts

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var nameEdit: EditText
    private lateinit var numberEdit: EditText
    private lateinit var mailEdit: EditText
    private lateinit var genderEdit: EditText
    private lateinit var memoEdit: EditText
    private lateinit var genderRadio: RadioGroup
    private lateinit var birthdayText: TextInputLayout
    private lateinit var birthdayButton: Button
    private lateinit var seeMoreButton: Button
    private lateinit var cancleButton: Button
    private lateinit var birthdayCalendar: CalendarView
    private lateinit var frm: FrameLayout
    private lateinit var saveButton: Button
    private lateinit var maleButton: RadioButton
    private lateinit var femaleButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEdit = findViewById(R.id.nameEdit)
        numberEdit = findViewById(R.id.numberEdit)
        mailEdit = findViewById(R.id.mailEdit)
        genderEdit = findViewById(R.id.genderEdit)
        memoEdit = findViewById(R.id.memoEdit)
        genderRadio = findViewById(R.id.genderRadio)
        maleButton = findViewById(R.id.maleButton)
        femaleButton = findViewById(R.id.femaleButton)
        birthdayText = findViewById(R.id.birthdayText)
        birthdayButton = findViewById(R.id.birthdayButton)
        seeMoreButton = findViewById(R.id.seeMoreButton)
        cancleButton = findViewById(R.id.cancleButton)
        birthdayCalendar = findViewById(R.id.birthdayCalendar)
        frm = findViewById(R.id.frm)
        saveButton = findViewById(R.id.saveButton)

        seeMore(seeMoreButton)
        showToast()
        clickDate()
        saveButton.setOnClickListener {
            saveData()
            finish()
        }

    }

    private fun seeMore(seeMoreButton: Button) {
        seeMoreButton.setOnClickListener {
            genderEdit.visibility = EditText.VISIBLE
            memoEdit.visibility = EditText.VISIBLE
            genderRadio.visibility = RadioGroup.VISIBLE
            birthdayText.visibility = TextInputLayout.VISIBLE
            birthdayButton.visibility = Button.VISIBLE
            seeMoreButton.visibility = Button.INVISIBLE
        }
    }

    private fun showToast() {

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

    private fun clickDate() {

        birthdayButton.setOnClickListener {
            Log.d("testt", "birthdayEdit clicked")
            birthdayCalendar.bringToFront()
            birthdayCalendar.visibility = View.VISIBLE
            frm.visibility = View.VISIBLE
        }
        birthdayCalendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$year-${month + 1}-$dayOfMonth"
            birthdayButton.setText(selectedDate)
            birthdayCalendar.visibility = View.GONE
        }
    }

    private fun saveData() {
        var gender: String? = null;
        if (maleButton.isChecked()) {
            gender = maleButton.getText().toString();
        } else if (femaleButton.isChecked()) {
            gender = femaleButton.getText().toString();
        }
        with(getSharedPreferences("userInformation", Context.MODE_PRIVATE).edit()) {
            putString("name", nameEdit.text.toString())
            putString("number", numberEdit.text.toString())
            putString("mail", mailEdit.text.toString())
            putString("birthday", birthdayButton.text.toString())
            putString("gender", gender)
            putString("memo", memoEdit.text.toString())
            apply()
        }
        Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()

    }
}


