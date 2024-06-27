package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import campus.tech.kakao.contacts.model.Contact
import java.time.LocalDate
import java.time.format.DateTimeParseException

class AddContactActivity : AppCompatActivity() {
    private lateinit var emailInput: EditText
    private lateinit var nameInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var birthdayInput: EditText
    private lateinit var genderInput: RadioGroup
    private lateinit var memoInput: EditText
    private var birthday: LocalDate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcontact)
        initiateInputFields()
        setMoreOptionsListener()
        setButtonsListener()

        onBackPressedDispatcher.addCallback(this) {
            alertBack()
        }
    }

    private fun initiateInputFields() {
        nameInput = findViewById(R.id.input_name)
        emailInput = findViewById(R.id.input_mail)
        phoneInput = findViewById(R.id.input_tel)
        birthdayInput = findViewById(R.id.input_birthday)
        memoInput = findViewById(R.id.input_memo)
        genderInput = findViewById(R.id.input_gender)

        birthdayInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showDatePicker()
            } else {
                verifyBirthday(birthdayInput.text.toString())
            }
        }
    }

    private fun setMoreOptionsListener() {
        findViewById<View>(R.id.more_options).setOnClickListener {
            appendOptions()
        }
    }

    private fun setButtonsListener() {
        findViewById<Button>(R.id.button_submit).setOnClickListener {
            if (validateInputs()) {
                saveSuccess()
            }
        }
        findViewById<Button>(R.id.button_cancel).setOnClickListener {
            alertBack()
        }
    }

    private fun getGender(): Int? {
        return when (genderInput.checkedRadioButtonId) {
            -1 -> null
            genderInput[GENDER_FEMALE].id -> GENDER_FEMALE
            genderInput[GENDER_MALE].id -> GENDER_MALE
            else -> -1
        }
    }

    fun validateInputs(): Boolean {
        if (checkNameEmpty()) {
            showToast("이름 값은 필수입니다")
            return false
        }
        if (checkPhoneNumberEmpty()) {
            showToast("전화번호 값은 필수입니다")
            return false
        }
        if (!checkEmailEmpty()) {
            if (!verifyEmail(emailInput.text.toString())) {
                showToast("잘못된 이메일 형식입니다")
                return false
            }
        }
        return true
    }

    private fun checkNameEmpty(): Boolean {
        return nameInput.text.isEmpty()
    }

    private fun checkPhoneNumberEmpty(): Boolean {
        return phoneInput.text.isEmpty()
    }

    private fun checkEmailEmpty(): Boolean {
        return emailInput.text.isEmpty()
    }

    fun verifyEmail(emailText: String): Boolean {
        val emailVerifyingRegex = Regex("^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$")
        return emailVerifyingRegex.matches(emailText)
    }

    private fun appendOptions() {
        findViewById<View>(R.id.more_options).visibility = View.GONE
        findViewById<View>(R.id.additional_inputs).visibility = View.VISIBLE
    }

    private fun saveSuccess() {
        returnResultAndFinish()
    }

    private fun cancel() {
        val intent = Intent()
        setResult(RESULT_CANCELED, intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showDatePicker() {
        val dialogInitialDate = birthday ?: LocalDate.now()
        val dialog =
            DatePickerDialog(
                this,
                null,
                dialogInitialDate.year,
                dialogInitialDate.monthValue,
                dialogInitialDate.dayOfMonth
            )
        dialog.setOnDateSetListener { _, year, month, dayOfMonth ->
            birthday = LocalDate.of(year, month, dayOfMonth)
            birthdayInput.setText(birthday?.toString())
        }
        dialog.show()
    }

    fun getBirthdayFromText(birthdayText: String): LocalDate? {
        return try {
            LocalDate.parse(birthdayText)
        } catch (
            e: DateTimeParseException
        ) {
            null
        }
    }

    private fun verifyBirthday(birthdayText: String) {
        birthday = getBirthdayFromText(birthdayText)
        birthdayInput.setText(birthday?.toString() ?: "")
    }

    companion object {
        const val GENDER_NONE = -1
        const val GENDER_FEMALE = 0
        const val GENDER_MALE = 1

        const val KEY_CONTACT = "contact"
    }

    private fun getTextOrNull(editText: EditText): String? {
        val str = editText.text.toString()
        return str.ifEmpty { null }
    }

    private fun returnResultAndFinish() {

        val intent = Intent()
        intent.putExtra(
            KEY_CONTACT,
            Contact(
                nameInput.text.toString(),
                phoneInput.text.toString(),
                getTextOrNull(emailInput),
                getGender(),
                birthday,
                getTextOrNull(memoInput)
            )
        )
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun alertBack() {
        val builder: androidx.appcompat.app.AlertDialog.Builder =
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setMessage("작성 중인 내용이 있습니다. 정말 나가시겠습니까?")
        builder.setPositiveButton("나가기") { _, _ ->
            cancel()
        }
        builder.setNegativeButton("작성하기") { _, _ ->
        }
        builder.create().show()
    }
}
