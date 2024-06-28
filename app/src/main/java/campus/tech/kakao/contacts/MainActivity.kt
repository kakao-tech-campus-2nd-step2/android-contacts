package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var moreButton: LinearLayout
    private lateinit var inputFields: Array<View>
    private lateinit var buttons: Array<Button>
    private lateinit var checkButtons: LinearLayout
    private lateinit var viewModel: SubViewModel

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
        buttons = arrayOf(
            findViewById(R.id.cancel),
            findViewById(R.id.save)
        )
        checkButtons = findViewById(R.id.checkbuttons)

        val clickListener = View.OnClickListener { view ->
            when (view) {
                moreButton -> showInputs()
                inputFields[3] -> showDatePickerDialog(inputFields[3])
                buttons[0] -> cancelContact()
                buttons[1] -> saveContact(inputsTrim())
            }
        }

        moreButton.setOnClickListener(clickListener)
        inputFields[3].setOnClickListener(clickListener)
        buttons[0].setOnClickListener(clickListener)
        buttons[1].setOnClickListener(clickListener)

        viewModel = ViewModelProvider(this).get(SubViewModel::class.java)

    }

    private fun getGenderVal(): String {
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val selectedId = radioGroup.checkedRadioButtonId
        if (selectedId != -1) {
            val radioButton = findViewById<RadioButton>(selectedId)
            val selectedText = radioButton.text.toString().trim()
            return selectedText
        }
        return ""
    }

    private fun inputsTrim(): Array<String> {
        val infos = Array(6) { "" }
        for (i in 0..5) {
            if (i == 4) {
                val gender = getGenderVal()
                infos[4] = gender
                continue
            }
            infos[i] = (inputFields[i] as EditText).text.toString().trim()
        }
        return infos
    }

    private fun showInputs() {
        var slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        moreButton.visibility = View.GONE
        GlobalScope.launch(Dispatchers.Main) {
            for (i in 3..5) {
                inputFields[i].visibility = View.VISIBLE
                inputFields[i].startAnimation(slideDown)
                delay(400)
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
        val name = (inputFields[0] as EditText).text.toString().trim()
        val phoneNumber = (inputFields[1] as EditText).text.toString().trim()
        Contacts.delContact(name, phoneNumber)
        Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_LONG).show()
    }

    private fun saveContact(infos: Array<String>) {
        if (infos[0] == "")
            Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_SHORT).show()
        else if (infos[1] == "")
            Toast.makeText(this, "전화 번호는 필수 값 입니다.", Toast.LENGTH_SHORT).show()
        else {
            if (Contacts.addContact(infos)) {
                Toast.makeText(this, "저장이 완료 되었습니다.", Toast.LENGTH_SHORT).show()
                viewModel.updateData(Contacts.getList())
            }
            else
                Toast.makeText(this, "이미 저장된 연락처 입니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
