package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var btnSave: TextView
    private lateinit var btnCancel: TextView
    private lateinit var btnMore: ImageView
    private lateinit var inputName: EditText
    private lateinit var inputPhoneNumber: EditText
    private lateinit var inputEmail: EditText
    private lateinit var inputBirthDay: EditText
    private lateinit var inputMemo: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var additionalInputLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        setViews()
        setEventListener()
    }

    private fun setViews() {
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)
        btnMore = findViewById(R.id.btnMore)
        inputName = findViewById(R.id.inputName)
        inputPhoneNumber = findViewById(R.id.inputPhoneNumber)
        inputEmail = findViewById(R.id.inputEmail)
        inputBirthDay = findViewById(R.id.inputBirthDay)
        inputMemo = findViewById(R.id.inputMemo)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        additionalInputLayout = findViewById(R.id.additionalInputLayout)

        inputBirthDay.setOnClickListener {
            showCalendar()
        }
    }

    private fun showCalendar(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { view, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedYear}-${selectedMonth + 1}-${selectedDay}"
                inputBirthDay.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun setEventListener() {
        setSaveButtonListener()
        setCancelButtonListener()
        setMoreButtonListener()
    }

    private fun setSaveButtonListener() {
        btnSave.setOnClickListener {
            if (isInputValid()) {
                saveUserData()
            } else {
                showToast("이름 또는 전화번호를 입력하세요")
            }
        }
    }

    private fun setCancelButtonListener() {
        val inputTextList = listOf(
            inputName,
            inputPhoneNumber,
            inputBirthDay,
            inputEmail,
            inputMemo
        )

        btnCancel.setOnClickListener {
            if (isInputFormFilled(inputTextList, radioGroupGender)) {
                showAlertDialog()
            } else {
                showToast("취소 되었습니다")
                finish()
            }
        }
    }

    private fun setMoreButtonListener() {
        btnMore.setOnClickListener {
            btnMore.visibility = View.GONE
            additionalInputLayout.visibility = View.VISIBLE
        }
    }

    private fun isInputValid(): Boolean {
        return inputName.text.isNotEmpty() && inputPhoneNumber.text.isNotEmpty()
    }

    private fun saveUserData() {
        val selectedGenderId = radioGroupGender.checkedRadioButtonId
        val gender = when (selectedGenderId) {
            R.id.radioBtnMan-> "남성"
            R.id.radioBtnWoman -> "여성"
            else -> ""
        }

        val intent = Intent().apply {
            putExtra("userData",
                UserData(inputName.text.toString(),
                    inputPhoneNumber.text.toString().toInt(),
                    inputEmail.text.toString(),
                    inputBirthDay.text.toString(),
                    gender,
                    inputMemo.text.toString()))
        }
        setResult(RESULT_OK, intent)
        finish()
        showToast("저장이 완료 되었습니다")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isInputFormFilled(inputTextList: List<EditText>, radioGroup: RadioGroup): Boolean {
        inputTextList.forEach {
            if (it.text.isNotEmpty()) {
                return true
            }
        }
        if (radioGroup.checkedRadioButtonId != -1) {
            return true
        }
        return false
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this)
            .setMessage("작성 중인 내용이 있습니다. 정말 나가시겠습니까?")
            .setPositiveButton("나가기") { _, _ ->
                showToast("취소 되었습니다")
                finish()
            }
            .setNegativeButton("작성하기", null)
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val resultData = data?.getStringExtra("resultKey")
        }
    }
}
