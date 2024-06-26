package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class AddContactActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var email: EditText
    private lateinit var birthday: TextView
    private lateinit var gender: RadioGroup
    private lateinit var memo: EditText
    private lateinit var cancel: TextView
    private lateinit var save: TextView
    private lateinit var viewMore: View
    private lateinit var groupMore: Group
    private lateinit var toast: Toast
    private val dateFormat = SimpleDateFormat(DATE_FORMAT)

    private val onBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                confirmToFinish()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        initViews()

        onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    private fun initViews() {
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)

        name = findViewById(R.id.et_name)
        phoneNumber = findViewById(R.id.et_phone_number)
        email = findViewById(R.id.et_mail)
        memo = findViewById(R.id.et_memo)

        gender = findViewById(R.id.rg_gender)

        groupMore = findViewById(R.id.group_more)

        viewMore = findViewById<View>(R.id.view_more)
        viewMore.apply {
            setOnClickListener {
                groupMore.isVisible = true
                isVisible = false
            }
        }

        birthday = findViewById<TextView>(R.id.tv_birthday)
        birthday.apply {
            setOnClickListener {
                showDatePickerDialog()
            }
        }

        cancel =
            findViewById<TextView>(R.id.tv_cancel).apply {
                setOnClickListener {
                    confirmToFinish()
                }
            }

        save =
            findViewById<TextView>(R.id.tv_save).apply {
                setOnClickListener {
                    if (checkDataValidation()) {
                        sendContactData()
                        finish()
                    }
                }
            }
    }

    private fun extractData(): JSONObject {
        return JSONObject(
            mapOf(
                CONTACT_NAME to name.text.toString(),
                CONTACT_PHONE_NUMBER to phoneNumber.text.toString(),
                CONTACT_MAIL to email.text.toString(),
                CONTACT_BIRTHDAY to birthday.text.toString(),
                CONTACT_GENDER to
                    gender
                        .run {
                            when (checkedRadioButtonId) {
                                R.id.rb_female -> GENDER_FEMALE
                                R.id.rb_male -> GENDER_MALE
                                else -> ""
                            }
                        },
                CONTACT_MEMO to memo.text.toString(),
            ),
        )
    }

    private fun sendContactData() {
        Intent(this, MainActivity::class.java).apply {
            putExtra(CONTACT_DATA, extractData().toString())
            setResult(RESULT_OK, this)
        }
    }

    private fun showDatePickerDialog() {
        DatePickerDialog(this).apply {
            val calendar = getBirthdayCalendar()
            getBirthdayCalendar().apply {
                updateDate(get(Calendar.YEAR), get(Calendar.MONTH), get(Calendar.DATE))
            }
            setOnDateSetListener { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                birthday.text = dateFormat.format(calendar.time)
            }
            show()
        }
    }

    private fun getBirthdayCalendar(): Calendar {
        val birthdayText =
            if (birthday.text.isNotEmpty()) birthday.text.toString() else DEFAULT_BIRTHDAY
        val birthdayDate = dateFormat.parse(birthdayText) ?: Date()
        return Calendar.getInstance().apply { time = birthdayDate }
    }

    private fun checkDataValidation(): Boolean {
        name.checkEmpty(R.string.warning_empty_name).also { isNullOrEmpty ->
            if (isNullOrEmpty) return false
        }
        phoneNumber.checkEmpty(R.string.warning_empty_phone_number).also { isNullOrEmpty ->
            if (isNullOrEmpty) return false
        }
        return true
    }

    private fun EditText.checkEmpty(
        @StringRes warningStringResId: Int,
    ): Boolean {
        return text.isNullOrEmpty().also { isNullOrEmpty ->
            if (isNullOrEmpty) {
                showToast(warningStringResId)
                (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).also {
                    requestFocus()
                    it.showSoftInput(this, 0)
                }
            }
        }
    }

    private fun showToast(
        @StringRes stringResId: Int,
    ) {
        toast.apply {
            setText(stringResId)
            show()
        }
    }

    private fun confirmToFinish() {
        if (name.text.isNotEmpty() ||
            phoneNumber.text.isNotEmpty() ||
            email.text.isNotEmpty() ||
            birthday.text.isNotEmpty() ||
            memo.text.isNotEmpty()
        ) {
            AlertDialog.Builder(this).apply {
                setMessage(R.string.warning_cancel)
                setPositiveButton(R.string.exit) { _, _ ->
                    finish()
                }
                setNegativeButton(R.string.write) { _, _ -> }
                show()
            }
        } else {
            finish()
        }
    }
}
