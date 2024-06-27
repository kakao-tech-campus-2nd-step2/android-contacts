package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameField: EditText = findViewById(R.id.name_field)
        val phoneField: EditText = findViewById(R.id.phone_field)
        val emailField: EditText = findViewById(R.id.email_field)
        val saveButton: Button = findViewById(R.id.save_button)
        val cancelButton: Button = findViewById(R.id.cancel_button)
        val moreButton: LinearLayout = findViewById(R.id.more_button)
        val additionalFields: LinearLayout = findViewById(R.id.additional_fields)
        val birthdayField: EditText = findViewById(R.id.birthday_field)
        val genderGroup: RadioGroup = findViewById(R.id.gender_group)
        val memoField: EditText = findViewById(R.id.memo_field)


        moreButton.setOnClickListener {
            moreButton.visibility = View.GONE
            additionalFields.visibility = View.VISIBLE
        }

        saveButton.setOnClickListener {
            val name = nameField.text.toString()
            val phone = phoneField.text.toString()
            val email = emailField.text.toString()
            val birthday = birthdayField.text.toString()
            val genderId = genderGroup.checkedRadioButtonId
            val memo = memoField.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_name_is_essential), Toast.LENGTH_SHORT).show()
            } else if (phone.isEmpty()) {
                Toast.makeText(this,
                    getString(R.string.toast_phone_number_is_essential), Toast.LENGTH_SHORT).show()
            } else {
                val message = getString(R.string.toast_saved)
                Log.d("MainActivity", "Name: $name")
                Log.d("MainActivity", "Phone: $phone")
                Log.d("MainActivity", "Email: $email")
                Log.d("MainActivity", "Birthday: $birthday")
                Log.d("MainActivity", "Gender ID: $genderId")
                Log.d("MainActivity", "Memo: $memo")
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
        cancelButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_canceled), Toast.LENGTH_SHORT).show()
        }

        birthdayField.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                birthdayField.setText(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }
    }
}
