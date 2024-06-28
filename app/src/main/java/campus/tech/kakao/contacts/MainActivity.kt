package campus.tech.kakao.contacts

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
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
    private lateinit var radioGroup: RadioGroup
    //private lateinit var viewModel: SubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moreButton = findViewById(R.id.moreButton)
        radioGroup = findViewById(R.id.radioGroup)
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
                moreButton -> {
                    showInputs()
                    hideKeyboard()
                }
                inputFields[3] -> showDatePickerDialog(inputFields[3])
                buttons[0] -> cancelContact()
                buttons[1] -> saveContact(inputsTrim())
            }
        }

        moreButton.setOnClickListener(clickListener)
        inputFields[3].setOnClickListener(clickListener)
        buttons[0].setOnClickListener(clickListener)
        buttons[1].setOnClickListener(clickListener)

        //viewModel = ViewModelProvider(this).get(SubViewModel::class.java)

    }

    private fun getGenderVal(): String {
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

    private fun hideKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            view.clearFocus()
        }
    }

    private fun delContent() {
        radioGroup.clearCheck()
        for (field in inputFields) {
            if (field is EditText)
                field.text.clear()
        }
    }

    private fun cancelContact() {
        delContent()
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
                val intent = Intent(this, SubActivity::class.java)
                startActivity(intent)
            } else
                Toast.makeText(this, "이미 저장된 연락처 입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onBackCheck() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("작성 중인 내용이 있습니다.\n정말 나가시겠습니까?")

        builder.setNegativeButton("작성하기") { dialog, which ->
        }

        builder.setPositiveButton("나가기") { dialog, which ->
            super.onBackPressed()
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onBackPressed() {
        val infos: Array<String> = inputsTrim()
        var hasContent = false

        for (i in 0..5)
            if (infos[i] != "") {
                hasContent = true
                break
            }

        if (hasContent) onBackCheck()
        else super.onBackPressed()
    }
}
