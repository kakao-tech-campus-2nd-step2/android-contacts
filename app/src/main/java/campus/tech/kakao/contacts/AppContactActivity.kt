package campus.tech.kakao.contacts

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import java.text.SimpleDateFormat
import java.util.*

class AppContactActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etBirthdate: EditText
    private lateinit var rbFemale: RadioButton
    private lateinit var rbMale: RadioButton
    private lateinit var etNotes: EditText
    private lateinit var btnMore: TextView
    private lateinit var moreForm: LinearLayout
    private lateinit var btnCancel: TextView
    private lateinit var btnSave: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_contact)

        etName = findViewById(R.id.et_name)
        etPhone = findViewById(R.id.et_phone)
        etEmail = findViewById(R.id.et_email)
        etBirthdate = findViewById(R.id.et_birthdate)
        rbFemale = findViewById(R.id.rb_female)
        rbMale = findViewById(R.id.rb_male)
        etNotes = findViewById(R.id.et_notes)
        btnMore = findViewById(R.id.btn_more)
        moreForm = findViewById(R.id.more_form)
        btnCancel = findViewById(R.id.btn_cancel)
        btnSave = findViewById(R.id.btn_save)

        etBirthdate.setOnClickListener {
            showDatePickerDialog(etBirthdate)
        }

        btnMore.setOnClickListener {
            moreForm.isVisible = true
            btnMore.isVisible = false
        }

        btnCancel.setOnClickListener {
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(this, "이름은 필수 값입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (phone.isEmpty()) {
                Toast.makeText(this, "전화 번호는 필수 값입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!phone.matches(Regex("\\d+"))) {
                Toast.makeText(this, "전화번호는 숫자만 입력 가능합니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = etEmail.text.toString()
            val birthdate = etBirthdate.text.toString()
            val gender = if (rbFemale.isChecked) "여성" else if (rbMale.isChecked) "남성" else ""
            val notes = etNotes.text.toString()

            val intent = Intent().apply {
                putExtra("name", name)
                putExtra("phone", phone)
                putExtra("email", email)
                putExtra("birthdate", birthdate)
                putExtra("gender", gender)
                putExtra("notes", notes)
            }
            setResult(RESULT_OK, intent)
            Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
            finish()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isFormFilled()) {
                    showExitConfirmationDialog()
                } else {
                    finish()
                }
            }
        })
    }

    private fun isFormFilled(): Boolean {
        return etName.text.isNotEmpty() ||
                etPhone.text.isNotEmpty() ||
                etEmail.text.isNotEmpty() ||
                etBirthdate.text.isNotEmpty() ||
                rbFemale.isChecked ||
                rbMale.isChecked ||
                etNotes.text.isNotEmpty()
    }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("작성중인 내용이 있습니다. 정말 나가시겠습니까?")
            .setCancelable(false)
            .setPositiveButton("네") { dialog, id ->
                finish()
            }
            .setNegativeButton("아니요") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                editText.setText(dateFormat.format(selectedDate.time))
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
