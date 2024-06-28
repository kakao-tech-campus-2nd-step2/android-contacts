package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class AddContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

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
            val name = nameField.text.toString().trim()
            val phone = phoneField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val birthday = birthdayField.text.toString().trim()
            val genderId = genderGroup.checkedRadioButtonId
            val memo = memoField.text.toString().trim()
            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("name", name)
                resultIntent.putExtra("phone", phone)
                resultIntent.putExtra("email", email)
                resultIntent.putExtra("birthday", birthday)
                resultIntent.putExtra("genderId", genderId)
                resultIntent.putExtra("memo", memo)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(
                    this@AddContactActivity, getString(R.string.toast_name_and_phone_is_essential),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        cancelButton.setOnClickListener {
            showExitConfirmationDialog()
        }

        birthdayField.setOnClickListener {
            showDatePickerDialog()
        }
    }

    override fun onBackPressed() {
        showExitConfirmationDialog()
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.dialog_confirm_exit))
            .setPositiveButton(getString(R.string.confirm_label)) { _, _ ->
                finish()
            }
            .setNegativeButton(getString(R.string.cancel_label)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            findViewById<EditText>(R.id.birthday_field).setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }
}
