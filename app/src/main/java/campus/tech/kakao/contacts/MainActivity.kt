package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.ContentValues
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import campus.tech.kakao.contacts.R.id.female

class MainActivity : AppCompatActivity() {
    private val etName: EditText by lazy { findViewById(R.id.name) }
    private val etPhone: EditText by lazy { findViewById(R.id.phone) }
    private val etmail: EditText by lazy { findViewById(R.id.email) }
    private val etmessage: EditText by lazy { findViewById(R.id.message) }
    private val btnsave: Button by lazy { findViewById(R.id.save) }
    private val btndeny: Button by lazy { findViewById(R.id.deny) }
    private val rgfemale: RadioButton by lazy { findViewById(R.id.female) }
    private val rgmale: RadioButton by lazy { findViewById(R.id.male) }
    private val btnbirthday: Button by lazy { findViewById(R.id.birthday) }
    private val etbirthday : EditText by lazy { findViewById(R.id.birthday_1) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnsave.setOnClickListener {
            saveContact()
            Toast.makeText(this, "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
        btndeny.setOnClickListener {
            Toast.makeText(this, "취소가 완료되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
        btnbirthday.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun setupPhoneNumberInput() {
        etPhone.inputType = InputType.TYPE_CLASS_PHONE
        etPhone.filters = arrayOf(InputFilter.LengthFilter(13))
        etPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {
                
            }
        })
    }


    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = String.format("%d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                etbirthday.setText(selectedDate)
            },
            year, month, day
        ).show()

    }

    private fun saveContact() {
        val name = etName.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val email = etmail.text.toString().trim()
        val message = etmessage.text.toString().trim()
        val birthday = btnbirthday.text.toString().trim()
        val gender = when {
            rgfemale.isChecked -> ContactsContract.CommonDataKinds.StructuredName.DATA1
            rgmale.isChecked -> ContactsContract.CommonDataKinds.StructuredName.DATA2
            else -> ContactsContract.CommonDataKinds.StructuredName.DATA3
        }

        if (name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && message.isNotEmpty()) {
            val values = ContentValues().apply {
                put(ContactsContract.Contacts.DISPLAY_NAME, name)
                put(ContactsContract.Contacts.IN_DEFAULT_DIRECTORY, birthday)
                put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
                put(ContactsContract.Contacts.IN_DEFAULT_DIRECTORY, email)
                put(ContactsContract.Contacts.IN_DEFAULT_DIRECTORY, message)
                put(ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID, gender)
            }

            contentResolver.insert(ContactsContract.Contacts.CONTENT_URI, values)
        } else {
            Toast.makeText(this, "정확한 값을 입력해주세요", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

