package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isEmpty
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val cancelButton: Button = findViewById(R.id.cancel)
        val saveButton: Button = findViewById(R.id.save)

        val name: EditText = findViewById(R.id.name)
        val phoneNumber: EditText = findViewById(R.id.phone_number)
        val mail: EditText = findViewById(R.id.mail)

        val viewMore: ConstraintLayout = findViewById(R.id.view_more)

        val birth: TextView = findViewById(R.id.birthday)
        val sex: RadioGroup = findViewById(R.id.sex_radio_group)
        val memo: EditText = findViewById(R.id.memo)

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!isInfoEmpty(name, phoneNumber, mail, birth, sex, memo)) {
                    buildAlertDialog(this@ContactActivity)
                } else finish()
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        cancelButton.setOnClickListener {
            val returnIntent: Intent = Intent()
            setResult(RESULT_CANCELED, returnIntent)
            finish()
        }

        saveButton.setOnClickListener {
            if (isValidInfo(name, phoneNumber)) {
                val contact: Contact = saveInfo(name, phoneNumber, mail, birth, sex, memo)
                Log.d("ContactActivity", contact.toString())
                val returnIntent: Intent = Intent(this,MainActivity::class.java)
                returnIntent.putExtra("res", contact)
                setResult(RESULT_OK, returnIntent)
                finish()
            }
        }

        viewMore.setOnClickListener {
            findViewById<ConstraintLayout>(R.id.more_infos).visibility = ConstraintLayout.VISIBLE
            viewMore.visibility = Button.GONE
        }

        birth.setOnClickListener {
            val calendar = Calendar.getInstance()
            val cYear = calendar.get(Calendar.YEAR)
            val cMonth = calendar.get(Calendar.MONTH)
            val cDay = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this,
                { _, year, month, dayOfMonth -> birth.text = "$year-%02d-%02d".format(month + 1, dayOfMonth) },
                cYear, cMonth, cDay).show()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("ContactActivity","onPause")
//        val backDialog = Dialog(this)
//        backDialog.
    }

    override fun onStop() {
        super.onStop()
        Log.d("ContactActivity","onStop")
    }

    fun isValidInfo(name: EditText, phoneNumber: EditText): Boolean {
        return if (!isValidName(name)) {
            false
        } else if (!isValidNumber(phoneNumber)) {
            false
        } else true
    }

    private fun isValidName(name: EditText): Boolean {
        return if (name.text.toString() == "") {
            Toast.makeText(this, "이름은 반드시 입력해야 합니다", Toast.LENGTH_SHORT).show()
            false
        } else true
    }

    private fun isValidNumber(phoneNumber: EditText): Boolean {
        val v : Regex = Regex("^[0-9]+$")
        return if (v.matches(phoneNumber.text.toString())) {
            true
        } else if (phoneNumber.text.toString() == "") {
            Toast.makeText(this, "전화번호는 반드시 입력해야 합니다", Toast.LENGTH_SHORT).show()
            false
        } else {
            Toast.makeText(this, "전화번호는 숫자로만 구성되어야 합니다", Toast.LENGTH_SHORT).show()
            false
        }
    }

    fun saveInfo(name: EditText, phoneNumber: EditText, mail: EditText,
                 birth: TextView, sex: RadioGroup, memo: EditText): Contact {
        val nameInfo: String = name.text.toString()
        val phoneNumberInfo: String = phoneNumber.text.toString()
        val mailInfo: String = mail.text.toString()
        val birthInfo: String = birth.text.toString()
        val sexInfo: Int = sex.checkedRadioButtonId
        val memoInfo: String = memo.text.toString()

        val contact: Contact = Contact(nameInfo, phoneNumberInfo,mailInfo, birthInfo, sexInfo, memoInfo)
        return contact
    }

    fun isInfoEmpty(name: EditText, phoneNumber: EditText, mail: EditText,
                    birth: TextView, sex: RadioGroup, memo: EditText): Boolean {
        return name.text.isEmpty() &&
        phoneNumber.text.isEmpty() &&
        mail.text.isEmpty() &&
        birth.text.isEmpty() &&
        sex.checkedRadioButtonId == -1 &&
        memo.text.isEmpty()
    }

    private fun buildAlertDialog(aContext: Context) {
        MaterialAlertDialogBuilder(aContext)
            .setMessage("작성 중인 내용이 있습니다. 정말 나가시겠습니까?")
            .setNegativeButton("취소") { dialog, which ->
            }
            .setPositiveButton("확인") { dialog, which ->
                finish()
            }
            .show()
    }
}