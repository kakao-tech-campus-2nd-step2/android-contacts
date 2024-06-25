package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var moreButton: LinearLayout
    private lateinit var inputFields: Array<View>
    private lateinit var checkButtons: Array<Button>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moreButton = findViewById(R.id.moreButton)
        inputFields = arrayOf(
            findViewById(R.id.inputName),
            findViewById(R.id.inputPhoneNumber),
            findViewById(R.id.inputMail),
            findViewById(R.id.inputBirth),
            findViewById(R.id.inputGender),
            findViewById(R.id.inputMemo)
        )
        checkButtons = arrayOf(
            findViewById(R.id.cancel),
            findViewById(R.id.save)
        )

        val clickListener = View.OnClickListener { view ->
            when(view) {
                moreButton -> showInputs()
                inputFields[0] -> Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_SHORT).show()
                inputFields[1] -> Toast.makeText(this, "전화 번호는 필수 값 입니다.", Toast.LENGTH_SHORT).show()
                inputFields[3] -> showDatePickerDialog(inputFields[3])
                checkButtons[0] -> cancelContact()
                checkButtons[1] -> saveContact()
            }
        }

        moreButton.setOnClickListener(clickListener)
        inputFields[0].setOnClickListener(clickListener)
        inputFields[1].setOnClickListener(clickListener)
        inputFields[3].setOnClickListener(clickListener)


    }

    private fun showInputs() {
        var slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        moreButton.visibility = View.GONE
            GlobalScope.launch(Dispatchers.Main) {
                for (i in 3..5) {
                    inputFields[i].visibility = View.VISIBLE
                    inputFields[i].startAnimation(slideDown)
                    delay(550)
                }
        }
    }

    private fun showDatePickerDialog(view: View) {
        if (view is EditText) {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedMonth = String.format("%02d", selectedMonth)
                    val formattedDay = String.format("%02d", selectedDay)
                    val selectedDate = "$selectedYear.$formattedMonth.$formattedDay"
                    view.setText(selectedDate)
                }, year, month, day)
            datePickerDialog.show()
        }
    }

    private fun cancelContact() {
        Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_LONG).show()
    }

    private fun saveContact() {
        Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
    }
}
