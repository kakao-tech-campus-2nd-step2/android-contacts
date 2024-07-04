package campus.tech.kakao.contacts

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class AddInforActivity : AppCompatActivity() {
    private var addName: String = ""
    private var addPhoneNumber: String = ""
    private var addEmail: String = ""
    private var addBirth: String = ""
    private var addMemo: String = ""
    private var addGender: String = ""
    private var memoEditText: EditText? = null
    private var genderRadioGroup: RadioGroup? = null
    private var birthTextView: TextView? = null
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
        val toMain = Intent(this@AddInforActivity, MainActivity::class.java)
        forMoreInformation.setOnClickListener {
            moreInformationBirth(this, addInfor)
            moreInformationGender(this, addInfor)
            moreInformationMemo(this, addInfor)
            removeMoreInformationButton(forMoreInformation)
        }
        saveButton.setOnClickListener {
            saveOrNotSave(saveData(inputName, inputPhoneNumber, inputEmail,"save"))
            if (saveData(inputName, inputPhoneNumber, inputEmail,"save")) {
                val resultIntent = Intent().apply {
                    putExtra("inforname", addName)
                    putExtra("infornumber", addPhoneNumber)
                    putExtra("inforEmail", addEmail)
                    putExtra("inforBirth", addBirth)
                    putExtra("inforMemo", addMemo)
                    putExtra("inforGender", addGender)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
                clearGlobalVariables()
            }
        }

        cancelButton.setOnClickListener {
            if (saveData(inputName, inputPhoneNumber, inputEmail,"cancel")){
                alertDialog(this)

            } else {
                cancelMessasge()
                finish()
            }

        }

    }

    private fun clearGlobalVariables() {
        addName = ""
        addPhoneNumber = ""
        addEmail = ""
        addBirth = ""
        addMemo = ""
        addGender = ""
        memoEditText = null
        genderRadioGroup = null
        birthTextView = null
    }

    private fun saveMessage() {
        Toast.makeText(this, "저장이 완료 되었습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun emptyDatamassage() {
        Toast.makeText(this, "이름과 전화번호는 필수 값입니다.", Toast.LENGTH_SHORT).show()
    }

    private fun cancelMessasge() {
        Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show()
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

    private fun createMemo(context: Context, hint: String): EditText {
        val editText = EditText(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(45, 16, 45, 16)
            }
            this.hint = hint
            setBackgroundResource(R.drawable.edittextborder)
        }
        return editText
    }

    private fun createSelectGender(context: Context, hint: String): LinearLayout {
        val container = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(45, 16, 45, 16)
            }
            setBackgroundResource(R.drawable.edittextborder)
            setPadding(16, 16, 16, 16)
        }
        val textView = TextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
            )
            this.text = hint
            this.textSize = 18f
        }
        val radioGroup = RadioGroup(context).apply {
            id = R.id.radioGroup
            orientation = RadioGroup.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        val radioButtonMale = RadioButton(context).apply {
            layoutParams = RadioGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            this.text = "Male"
        }

        val radioButtonFemale = RadioButton(context).apply {
            layoutParams = RadioGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            this.text = "Female"
        }

        radioGroup.addView(radioButtonMale)
        radioGroup.addView(radioButtonFemale)

        container.addView(textView)
        container.addView(radioGroup)

        return container
    }

    private fun moreInformationBirth(context: Context, addInfor: LinearLayout) {
        val textView = createSelectBirth(context, "생일")
        birthTextView = textView
        addInfor.addView(textView)
    }

    private fun moreInformationGender(context: Context, addInfor: LinearLayout) {
        val layout = createSelectGender(context, "성별")
        val radioGroup = layout.findViewById<RadioGroup>(R.id.radioGroup)
        genderRadioGroup = radioGroup
        addInfor.addView(layout)
    }

    private fun moreInformationMemo(context: Context, addInfor: LinearLayout) {
        val editText = createMemo(context, "메모")
        memoEditText = editText
        addInfor.addView(editText)
    }

    private fun removeMoreInformationButton(button: View) {
        val parentLayoutButton = button.parent as? ConstraintLayout
        parentLayoutButton?.removeView(button)
    }

    private fun checkEmptyDataforSave(addName: String, addPhoneNumber: String): Boolean {
        return addName.isNotEmpty() && addPhoneNumber.isNotEmpty()
    }

    private fun checkEmptyDataforCencel(
        addName: String,
        addPhoneNumber: String,
        addEmail: String
    ): Boolean {
        return addName.isNotEmpty() || addPhoneNumber.isNotEmpty() || addEmail.isNotEmpty()
    }

    fun saveData(
        inputName: EditText,
        inputPhoneNumber: EditText,
        inputEmail: EditText,
        mode: String
    ): Boolean {
        addName = inputName.text.toString()
        addPhoneNumber = inputPhoneNumber.text.toString()
        addEmail = inputEmail.text.toString()
        if (genderRadioGroup != null && birthTextView != null && memoEditText != null) {
            addGender =
                genderRadioGroup!!.findViewById<RadioButton>(genderRadioGroup!!.checkedRadioButtonId)?.text.toString()
            addBirth = birthTextView!!.text.toString()
            addMemo = memoEditText!!.text.toString()
        }

        return when (mode) {
            "save" -> checkEmptyDataforSave(addName, addPhoneNumber)
            "cancel" -> checkEmptyDataforCencel(addName, addPhoneNumber, addEmail)
            else -> false
        }
    }
    private fun saveOrNotSave(checking: Boolean) {
        if (checking) {
            saveMessage()
        } else {
            emptyDatamassage()
        }
    }
    fun alertDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("타이틀 입니다.")
            .setMessage("작성하시던 내용이 있습니다.")
            .setPositiveButton("취소하기",
                DialogInterface.OnClickListener { dialog, id ->
                    finish()

                })
            .setNegativeButton("돌아가기",
                DialogInterface.OnClickListener { dialog, id ->
                })
        builder.show()
    }
}
