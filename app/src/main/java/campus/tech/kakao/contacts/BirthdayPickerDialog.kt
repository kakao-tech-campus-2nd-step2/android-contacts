package campus.tech.kakao.contacts

import android.app.AlertDialog
import android.content.Context
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.view.LayoutInflater
import android.view.View
import java.util.Calendar

class BirthdayPickerDialog(
    private val context: Context,
    private val editText: EditText
) {
    private var yy: String = ""
    private var mm: String = ""
    private var dd: String = ""
    private var check: Boolean = false

    fun show() {
        val dialogBuilder = AlertDialog.Builder(context)
        val pickerLayout: View = LayoutInflater.from(context).inflate(R.layout.dialog_date_picker, null)
        val datePicker: DatePicker = pickerLayout.findViewById(R.id.datepicker)
        val pickerBtn = Button(context).apply {
            text = "확인"
        }

        // 현재 날짜로 DatePicker의 최대 날짜를 설정
        val today = Calendar.getInstance()
        datePicker.maxDate = today.timeInMillis

        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH),
            DatePicker.OnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
                yy = year.toString()
                mm = (monthOfYear + 1).toString()
                dd = dayOfMonth.toString()
                check = true
            })

        pickerBtn.setOnClickListener {
            updateBirthdayEditText()
            (it.parent as? AlertDialog)?.dismiss()
        }

        dialogBuilder.setView(pickerLayout)
        dialogBuilder.setPositiveButton("확인") { _, _ -> updateBirthdayEditText() }
        dialogBuilder.setNegativeButton("취소", null)
        dialogBuilder.show()
    }

    private fun updateBirthdayEditText() {
        if (check) {
            editText.setText("$yy-$mm-$dd")
        }
    }
}
