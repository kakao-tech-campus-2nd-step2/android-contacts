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

class ContactAddActivity : AppCompatActivity() {
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
    private lateinit var saveName: String
    private lateinit var savePhoneNumber: String
    private lateinit var saveMail: String
    private lateinit var saveBirth: String
    private lateinit var saveGender: String
    private lateinit var saveMemo: String

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
            cleanUpData()
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_LONG).show()
        }

        save.setOnClickListener {
            saveContact()
            val contactValidation: String? = checkContact()
            if (contactValidation.isNullOrEmpty()) {
                val intent = Intent()
                intent.putExtra(
                    "contact",
                    Contact(saveName, savePhoneNumber, saveMail, saveBirth, saveGender, saveMemo)
                )
                setResult(RESULT_OK, intent)
                finish()
            } else {
                showToast(contactValidation)
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

    private fun checkContact(): String? {
        return when {
            saveName.isEmpty() -> "이름을 입력해주세요"
            savePhoneNumber.isEmpty() -> "전화번호를 입력해주세요"
            else -> null
        }
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (checkData()) {
                val builder = AlertDialog.Builder(this@ContactAddActivity)
                builder.setMessage("작성 중인 내용이 있습니다. 정말 나가시겠습니까?")
                    .setPositiveButton("나가기",
                        DialogInterface.OnClickListener { dialog, _ ->
                            dialog.dismiss()
                            val intent = Intent(this@ContactAddActivity, MainActivity::class.java)
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
        return name.text.toString().isNotEmpty() ||
                phoneNumber.text.toString().isNotEmpty() ||
                mail.text.toString().isNotEmpty() ||
                birth.text.toString().isNotEmpty() ||
                genderRadio.checkedRadioButtonId != -1 ||
                memo.text.toString().isNotEmpty()
    }

    private fun cleanUpData() {
        name.text = null
        phoneNumber.text = null
        mail.text = null
        birth.text = null
        genderRadio.clearCheck()
        memo.text = null
    }

    private fun saveContact() {
        saveName = name.text.toString()
        savePhoneNumber = phoneNumber.text.toString()
        saveMail = mail.text.toString()
        saveBirth = birth.text.toString()
        val selectedId = genderRadio.checkedRadioButtonId
        saveGender = when (selectedId) {
            R.id.male -> "남성"
            R.id.female -> "여성"
            else -> ""
        }
        saveMemo = memo.text.toString()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}