package campus.tech.kakao.contacts

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ContactAdd : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var mail: EditText
    private lateinit var extraBtn: LinearLayout
    private lateinit var birth: EditText
    private lateinit var genderRadio: RadioGroup
    private lateinit var memo: EditText
    private lateinit var cancel: Button
    private lateinit var save: Button
    private lateinit var datePickerDialog: DatePickerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_add)

        findViews()

        extraBtn.setOnClickListener {
            birth.visibility = View.VISIBLE
            genderRadio.visibility = View.VISIBLE
            memo.visibility = View.VISIBLE
            extraBtn.visibility = View.GONE
        }

        birth.setOnClickListener {
            showDatePickerDialog()
        }

        cancel.setOnClickListener {
            name.text = null
            phoneNumber.text = null
            mail.text = null
            birth.text = null
            genderRadio.clearCheck()
            memo.text = null
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_LONG).show()
        }

        save.apply {
            this.setOnClickListener {
                if (checkContact()) {
                    val intent: Intent = Intent()
                    intent.putExtra("name", name.text.toString())
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun findViews() {
        name = findViewById(R.id.name)
        phoneNumber = findViewById(R.id.phoneNumber)
        mail = findViewById(R.id.mail)
        extraBtn = findViewById(R.id.extraBtn)
        birth = findViewById(R.id.birth)
        genderRadio = findViewById(R.id.genderRadio)
        memo = findViewById(R.id.memo)
        cancel = findViewById(R.id.cancel)
        save = findViewById(R.id.save)
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                val sdf = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
                birth.setText(sdf.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun checkContact(): Boolean {
        if (name.getText().isEmpty()) {
            Toast.makeText(this, "이름을 반드시 적어야 합니다", Toast.LENGTH_LONG).show()
            return false
        } else if (phoneNumber.getText().isEmpty()) {
            Toast.makeText(this, "전화번호를 반드시 적어야 합니다", Toast.LENGTH_LONG).show()
            return false
        } else {
            Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_LONG).show()
            return true
        }
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (checkData()) {
                val builder = AlertDialog.Builder(this@ContactAdd)
                builder.setMessage("작성 중인 내용이 있습니다. 정말 나가시겠습니까?")
                    .setPositiveButton("나가기",
                        DialogInterface.OnClickListener { dialog, _ ->
                            dialog.dismiss()
                            val intent = Intent(this@ContactAdd, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        })
                    .setNegativeButton("작성하기",
                        DialogInterface.OnClickListener { dialog, _ ->
                            dialog.dismiss()
                        })
                builder.show()
            } else {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun checkData(): Boolean {
        return name.text.isNotEmpty() ||
                phoneNumber.text.isNotEmpty() ||
                mail.text.isNotEmpty() ||
                birth.text.isNotEmpty() ||
                genderRadio.checkedRadioButtonId != -1 ||
                memo.text.isNotEmpty()
    }

}