package campus.tech.kakao.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameET = findViewById<EditText>(R.id.nameET)
        val phoneET = findViewById<EditText>(R.id.phoneET)
        val mailET = findViewById<EditText>(R.id.mailET)
        val moreBtnLayout = findViewById<LinearLayout>(R.id.moreBtnLayout)
        val moreETLayout = findViewById<LinearLayout>(R.id.moreETLayout)
        val detailBtn = findViewById<TextView>(R.id.detailBtn)
        val birthDayET = findViewById<EditText>(R.id.birthDayET)
        val genderRG = findViewById<RadioGroup>(R.id.gender_RG)
        val memoET = findViewById<EditText>(R.id.memoET)
        val cancelBtn = findViewById<Button>(R.id.cancelBtn)
        val saveBtn = findViewById<Button>(R.id.saveBtn)

        phoneET.inputType = InputType.TYPE_CLASS_NUMBER

        nameET.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
        nameET.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            source.filter { it.isLetter() || it.isWhitespace() }
        })

        detailBtn.setOnClickListener {
            showMoreFields(moreBtnLayout, moreETLayout)
        }

        birthDayET.setOnClickListener {
            showDatePickerDialog(birthDayET)
        }

    }

    private fun showMoreFields(moreBtnLayout: LinearLayout, moreETLayout: LinearLayout) {
        moreBtnLayout.visibility = View.GONE
        moreETLayout.visibility = View.VISIBLE
    }

    private fun showDatePickerDialog(birthDayET: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            birthDayET.setText("$selectedYear-${selectedMonth + 1}-$selectedDay")
        }, year, month, day)
        datePickerDialog.show()
    }

}