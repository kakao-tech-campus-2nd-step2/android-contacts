package campus.tech.kakao.contacts

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import android.content.Intent
import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameET = findViewById<EditText>(R.id.nameET)
        val phoneET = findViewById<EditText>(R.id.phoneET)
        val mailET = findViewById<EditText>(R.id.mailET)
        val moreBtnLayout = findViewById<LinearLayout>(R.id.moreBtnLayout)
        val moreETLayout = findViewById<LinearLayout>(R.id.moreETLayout)
        val detailBtn = findViewById<TextView>(R.id.detailBtn)
        val birthDayET = findViewById<EditText>(R.id.birthDayET)
        val genderRG = findViewById<RadioGroup>(R.id.gender_RG)
        val memoET = findViewById<EditText>(R.id.memoET)
        val cancelBtn = findViewById<Button>(R.id.cancelBtn)
        val saveBtn = findViewById<Button>(R.id.saveBtn)

        phoneET.inputType = InputType.TYPE_CLASS_NUMBER
        nameET.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
        nameET.filters = arrayOf(InputFilter { source, _, _, _, _, _ -> source.filter { it.isLetter() || it.isWhitespace() } })

        detailBtn.setOnClickListener { showMoreFields(moreBtnLayout, moreETLayout) }
        birthDayET.setOnClickListener { showDatePickerDialog(birthDayET) }
        saveBtn.setOnClickListener { saveCheck(nameET, phoneET, mailET, birthDayET, genderRG, memoET) }
        cancelBtn.setOnClickListener { showToast("취소되었습니다") }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val builder = AlertDialog.Builder(this)
        builder.setMessage("작성 중인 내용이 있습니다. 정말 나가시겠습니까?")
            .setCancelable(false)
            .setPositiveButton("나가기") { dialog, id ->
                val intent = Intent(this, ContactListActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("작성하기") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    private fun showMoreFields(moreBtnLayout: LinearLayout, moreETLayout: LinearLayout) {
        moreBtnLayout.visibility = View.GONE
        moreETLayout.visibility = View.VISIBLE
    }

    private fun showDatePickerDialog(birthDayET: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            birthDayET.setText("$selectedYear-${selectedMonth + 1}-$selectedDay")
        }, year, month, day)
        datePickerDialog.show()
    }

    private fun saveCheck(nameET: EditText, phoneET: EditText, mailET: EditText, birthDayET: EditText, genderRG: RadioGroup, memoET: EditText) {
        val name = nameET.text.toString()
        val phone = phoneET.text.toString()
        val mail = mailET.text.toString()
        val birthday = birthDayET.text.toString()
        val memo = memoET.text.toString()

        when {
            name.isEmpty() && phone.isEmpty() -> {
                showToast("이름과 전화번호를 입력하세요")
            }
            name.isEmpty() -> {
                showToast("이름을 입력하세요")
            }
            phone.isEmpty() -> {
                showToast("전화번호를 입력하세요")
            }
            else -> {
                showToast("저장이 완료되었습니다")
                val resultIntent = Intent().apply {
                    putExtra("name", name)
                    putExtra("phone", phone)
                    putExtra("email", mail)
                    putExtra("birthday", birthday)
                    putExtra("memo", memo)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}