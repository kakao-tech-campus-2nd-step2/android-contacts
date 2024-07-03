package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.ContactsContract
import android.text.InputFilter
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

        private val sharedPrefs by lazy {
            getSharedPreferences("contacts", Context.MODE_PRIVATE)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val btnSave: Button by lazy { findViewById(R.id.save) }
            val btnDeny: Button by lazy { findViewById(R.id.deny) }
            val editName : EditText by lazy { findViewById(R.id.name)}
            val editphone : EditText by lazy { findViewById(R.id.phone)}
            btnSave.setOnClickListener {
                saveContact()
                if (editName.text.isNotEmpty()&& editphone.text.isNotEmpty()) {
                    Toast.makeText(this, "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(
                        this,
                        CollectionActivity::class.java
                    )
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "정확한 값을 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }
            btnDeny.setOnClickListener {
                Toast.makeText(this, "취소가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(
                    this,
                    CollectionActivity::class.java
                )
                startActivity(intent)

            }
            val btnbirthday: Button by lazy { findViewById(R.id.birthday) }
            btnbirthday.setOnClickListener {
                showDatePickerDialog()
            }
        }

        private data class Contact(
            val name: String,
            val phone: String,
            val gender: String,
            val email: String,
            val message: String,
            val birthday: String
        )
        private val etName: EditText by lazy { findViewById(R.id.name) }
        private val etPhone: EditText by lazy { findViewById(R.id.phone) }
        private val etmail: EditText by lazy { findViewById(R.id.email) }
        private val etmessage: EditText by lazy { findViewById(R.id.message) }
        val btnbirthday: Button by lazy { findViewById(R.id.birthday) }
        private val rgfemale: RadioButton by lazy { findViewById(R.id.female) }
        private val rgmale: RadioButton by lazy { findViewById(R.id.male) }
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

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val contact = Contact(
                    name = name,
                    phone = phone,
                    email = email,
                    message = message,
                    gender = gender,
                    birthday = birthday
                )

                saveContactToSharedPrefs(contact)
                writeContactsToXml(getAllContacts())

                if (name.isNotEmpty()&&phone.isNotEmpty()) {
                    Toast.makeText(this, "연락처가 저장되었습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(
                        this,
                        CollectionActivity::class.java
                    )
                    startActivity(intent)
                }

            } else {
                Toast.makeText(this, "정확한 값을 입력해주세요", Toast.LENGTH_SHORT).show()
                val intent = Intent(
                    this,
                    CollectionActivity::class.java
                )
                startActivity(intent)
            }
        }

        private fun saveContactToSharedPrefs(contact: Contact) {
            with(sharedPrefs.edit()) {
                putString("name", contact.name)
                putString("phone", contact.phone)
                putString("email", contact.email)
                putString("message", contact.message)
                putString("gender", contact.gender)
                putString("birthday", contact.birthday)
                apply()
            }
        }

        private fun getAllContacts(): List<Contact?> {
            val name = sharedPrefs.getString("name", "") ?: ""
            val phone = sharedPrefs.getString("phone", "") ?: ""
            val email = sharedPrefs.getString("email", "") ?: ""
            val message = sharedPrefs.getString("message", "") ?: ""
            val gender = sharedPrefs.getString("gender", "") ?: ""
            val birthday = sharedPrefs.getString("birthday", "") ?: ""

            return listOf(
                Contact(
                    name = name,
                    phone = phone,
                    email = email,
                    message = message,
                    gender = gender,
                    birthday = birthday
                )
            )
        }


        private fun writeContactsToXml(allContacts: List<Contact?>) {

        }
        private val etbirthday: EditText by lazy { findViewById(R.id.birthday_1) }
        private fun showDatePickerDialog() {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate =
                        String.format("%d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                    etbirthday.setText(selectedDate)
                },
                year, month, day
            ).show()
        }

        private fun setupPhoneNumberInput() {
            etPhone.inputType = InputType.TYPE_CLASS_PHONE

            val phoneNumberFilter = InputFilter { source, start, end, dest, dstart, dend ->
                val phoneNumberRegex = Regex("^\\+?\\d{0,13}\$")
                if (phoneNumberRegex.matches(source.toString())) {
                    null
                } else {
                    ""
                }
            }
            etPhone.filters = arrayOf(phoneNumberFilter)
        }
    }



