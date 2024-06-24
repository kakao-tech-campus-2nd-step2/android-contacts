package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggleButton: ImageView = findViewById(R.id.toggle)
        val additionalFieldsLayout: LinearLayout = findViewById(R.id.additionalFieldsLayout)
        val birthdayEditText: EditText = findViewById(R.id.birthdayEditText)

        toggleButton.setOnClickListener {
            if (additionalFieldsLayout.visibility == View.GONE) {
                additionalFieldsLayout.visibility = View.VISIBLE
            } else {
                additionalFieldsLayout.visibility = View.GONE
            }
        }

        birthdayEditText.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            val birthdayEditText: EditText = findViewById(R.id.birthdayEditText)
            birthdayEditText.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }
}

