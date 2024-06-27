package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private var addName: String = ""
private var addPhoneNumber: String = ""
private var addEmail: String = ""
private var addBirth: String = ""
private var addMemo: String = ""
private var addGender: String = ""
class AddInfor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_infor)
        val forMoreInformation = findViewById<Button>(R.id.forMoreInformation)
        val addInfor = findViewById<LinearLayout>(R.id.addInfor)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val inputName = findViewById<EditText>(R.id.inputName)
        val inputPhoneNumber = findViewById<EditText>(R.id.inputPhoneNumber)
    }
}
private fun saveMessage(saveButton: Button) {
    Toast.makeText(saveButton.context, "저장이 완료 되었습니다.", Toast.LENGTH_SHORT).show()
}
private fun emptyDatamassage(saveButton: Button) {
    Toast.makeText(saveButton.context, "이름과 전화번호는 필수 값입니다.", Toast.LENGTH_SHORT).show()
}
private fun cancelMessasge(cancelButton: Button) {
    Toast.makeText(cancelButton.context, "취소 되었습니다.", Toast.LENGTH_SHORT).show()
}
private fun createSelectBirth(context: Context, hint: String): TextView {
    return TextView(context).apply {
        layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(45, 16, 45, 16)
        }
        text = hint
        textSize = 18f
        setBackgroundResource(R.drawable.edittextborder)
        setOnClickListener {
            selectDate(context, this)
        }
    }
}
private fun selectDate(context: Context, textView: TextView) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "${selectedYear}.${selectedMonth + 1}.${selectedDay}"
            textView.text = selectedDate
        },
        year, month, day
    )

    datePickerDialog.show()
}