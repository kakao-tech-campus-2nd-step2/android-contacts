package campus.tech.kakao.contacts

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val etName = findViewById<EditText>(R.id.et_name)
        val etPhone = findViewById<EditText>(R.id.et_phone)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etBirthdate = findViewById<EditText>(R.id.et_birthdate)
        val rbFemale = findViewById<RadioButton>(R.id.rb_female)
        val rbMale = findViewById<RadioButton>(R.id.rb_male)
        val etNotes = findViewById<EditText>(R.id.et_notes)
        val btnMore = findViewById<TextView>(R.id.btn_more)
        val moreForm = findViewById<LinearLayout>(R.id.more_form)
        val btnCancel = findViewById<TextView>(R.id.btn_cancel)
        val btnSave = findViewById<TextView>(R.id.btn_save)

        // EditText 클릭 시에만 키보드를 표시
        setupEditTextFocus(etName)
        setupEditTextFocus(etPhone)
        setupEditTextFocus(etEmail)
        setupEditTextFocus(etNotes)

        // 생일 입력을 위한 DatePickerDialog 설정
        etBirthdate.setOnClickListener {
            showDatePickerDialog(etBirthdate)
        }

        btnMore.setOnClickListener {
            moreForm.visibility = LinearLayout.VISIBLE
            btnMore.visibility = View.GONE
        }

        btnCancel.setOnClickListener {
            moreForm.visibility = LinearLayout.GONE
            btnMore.visibility = View.VISIBLE
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()
            val birthdate = etBirthdate.text.toString()
            val gender = if (rbFemale.isChecked) "여성" else if (rbMale.isChecked) "남성" else ""
            val notes = etNotes.text.toString()

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

        // Back 버튼 콜백
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitConfirmationDialog()
            }
        })
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

    private fun setupEditTextFocus(editText: EditText) {
        editText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                showKeyboard(v)
            } else {
                hideKeyboard(v)
            }
        }
    }

    private fun showKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "${selectedYear}-${String.format("%02d", selectedMonth + 1)}-${String.format("%02d", selectedDay)}"
                editText.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
