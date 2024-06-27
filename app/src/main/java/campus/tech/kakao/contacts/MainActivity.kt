package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.ContentValues
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings.Global
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import campus.tech.kakao.contacts.R.id.female
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
    private val etbirthday: EditText by lazy { findViewById(R.id.birthday_1) }
    private val rgGender: RadioGroup by lazy { findViewById(R.id.Gender) }
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "PhoneCollection"
        ).build()
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

    @Entity(tableName = "contacts")
    data class Contact(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val name: String,
        val phone: String,
        val gender: String,
        val email: String,
        val message: String,
        val birthday: String
    )

    @Dao
    interface ContactDao {
        @Insert
        suspend fun insert(contact: Contact)

        @Update
        suspend fun update(contact: Contact)

        @Delete
        suspend fun delete(contact: Contact)

        @Query("SELECT * FROM contacts")
        fun getAllContacts(): List<Contact>
    }

    abstract class AppDatabase : RoomDatabase() {
        abstract fun contactDao(): ContactDao
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
            val contact = Contact(
                name = name,
                phone = phone,
                email = email,
                message = message,
                gender = gender,
                birthday = birthday
            )

            GlobalScope.launch {
                db.contactDao().insert(contact)
            }

            Toast.makeText(this, "연락처가 저장되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "정확한 값을 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }
}

