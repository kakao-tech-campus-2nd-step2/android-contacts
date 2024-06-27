package campus.tech.kakao.contacts

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import campus.tech.kakao.contacts.model.Contact
import java.time.LocalDate
import java.time.format.DateTimeParseException

class AddContactActivity : AppCompatActivity() {
    lateinit var emailInput: EditText
    lateinit var nameInput: EditText
    lateinit var phoneInput: EditText
    lateinit var birthdayInput: EditText
    lateinit var genderInput: RadioGroup
    lateinit var memoInput: EditText

    var birthday: LocalDate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcontact)
        initiateInputFields()
        setMoreOptionsListener()
        setButtonsListener()

        onBackPressedDispatcher.addCallback(this){
            alertBack()
        }
    }

    fun initiateInputFields() {
        nameInput = findViewById(R.id.input_name)
        emailInput = findViewById(R.id.input_mail)
        phoneInput = findViewById(R.id.input_tel)
        birthdayInput = findViewById(R.id.input_birthday)
        memoInput = findViewById(R.id.input_memo)
        genderInput = findViewById<RadioGroup>(R.id.input_gender)

        birthdayInput.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                showDatePicker()
            } else {
                verifyBirthday(birthdayInput.text.toString())
            }
        }
    }

    fun setMoreOptionsListener() {
        findViewById<View>(R.id.more_options).setOnClickListener {
            appendOptions()
        }
    }

    fun setButtonsListener() {
        findViewById<Button>(R.id.button_submit).setOnClickListener {
            if (validateInputs()) {
                saveSuccess()
            }
        }
        findViewById<Button>(R.id.button_cancel).setOnClickListener {
            alertBack()
        }
    }

    fun getGender(): Int? {
        if(genderInput.checkedRadioButtonId == -1)
            return null
        if(genderInput.checkedRadioButtonId == genderInput[GENDER_FEMALE].id)
            return GENDER_FEMALE
        else if(genderInput.checkedRadioButtonId == genderInput[GENDER_MALE].id)
            return GENDER_MALE
        else return -1
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

    fun checkNameEmpty(): Boolean {
        return nameInput.text.isEmpty()
    }

    fun checkPhoneNumberEmpty(): Boolean {
        return phoneInput.text.isEmpty()
    }

    fun checkEmailEmpty(): Boolean {
        return emailInput.text.isEmpty()
    }

    fun verifyEmail(emailText: String): Boolean {
        val emailVerifyingRegex = Regex("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$")
        return emailVerifyingRegex.matches(emailText)
    }

    fun appendOptions() {
        findViewById<View>(R.id.more_options).visibility = View.GONE
        findViewById<View>(R.id.additional_inputs).visibility = View.VISIBLE
    }

    fun saveSuccess() {
        returnResultAndFinish()
    }

    fun cancel() {
        val intent: Intent = Intent()
        setResult(RESULT_CANCELED, intent)
        finish()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showDatePicker() {
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

    fun verifyBirthday(birthdayText: String) {
        birthday = getBirthdayFromText(birthdayText)
        birthdayInput.setText(birthday?.toString() ?: "")
    }

    companion object {
        const val GENDER_NONE = -1
        const val GENDER_FEMALE = 0
        const val GENDER_MALE = 1

        const val KEY_CONTACT = "contact"
    }

    fun getTextOrNull(editText: EditText): String?{
        val str = editText.text.toString()
        return str.ifEmpty { null }
    }

    fun returnResultAndFinish() {

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

    fun alertBack(){
        val builder:androidx.appcompat.app.AlertDialog.Builder = androidx.appcompat.app.AlertDialog.Builder(this)
            .setMessage("작성 중인 내용이 있습니다. 정말 나가시겠습니까?")
        builder.setPositiveButton("나가기") { _, _ ->
            cancel() }
        builder.setNegativeButton("작성하기") {_, _ ->
        }
        builder.create().show()
    }
}
