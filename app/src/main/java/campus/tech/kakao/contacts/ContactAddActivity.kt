package campus.tech.kakao.contacts

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ContactAddActivity : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var tel: EditText
    lateinit var mail: EditText
    lateinit var bday: TextView
    lateinit var genderRadioGroup: RadioGroup
    lateinit var memo: EditText
    fun showExitConfirmDialog() {
        AlertDialog.Builder(this).setTitle("").setMessage(R.string.back_check_message)
            .setPositiveButton("나가기") { _, _ ->
                finish()
            }.setNegativeButton("작성하기", null).create().show()
    }

    fun startCalenderDialog(textView: TextView) {
        val datePickerDialog = DatePickerDialog(this)
        datePickerDialog.updateDate(2000, 0, 1)

        datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
            Log.d("testt", getString(R.string.birthday, year, month + 1, dayOfMonth))
            textView.text = getString(R.string.birthday, year, month + 1, dayOfMonth)
        }

        datePickerDialog.show()
    }

    fun extendEditTextList(view: View, sizeId: Int) {
        var layoutParams = view.layoutParams
        layoutParams.height = resources.getDimensionPixelSize(sizeId)
        view.layoutParams = layoutParams
    }

    fun toggleViewVisibility(view: View) {
        view.visibility = when (view.visibility) {
            GONE -> VISIBLE
            else -> GONE
        }
    }

    fun cancelAddContact() {
        finish()
        showToast("취소 되었습니다.")
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun setFocus(view: View) {
        view.requestFocus()
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0);
    }

    fun isValidContact(views: List<EditText>): Boolean {
        views.forEach {
            if (it.text.isEmpty()) {
                showToast(resources.getString(R.string.essential_value, it.hint.toString()))
                setFocus(it)
                return false
            }
        }
        return true
    }

    fun isWriting(views: List<View>): Boolean {
        views.forEach {
            when (it) {
                is EditText -> if (it.text.isNotEmpty()) return true
                is TextView -> if (it.text.isNotEmpty()) return true
                is RadioGroup -> if (getGender() == null) return true
            }
        }
        return false
    }

    fun getGender(): Gender? = when (genderRadioGroup.checkedRadioButtonId) {
        R.id.male -> Gender.MALE
        R.id.female -> Gender.FEMALE
        else -> null
    }


    fun submitContact() {
        val newContact = Contact(
            name.text.toString(),
            tel.text.toString(),
            mail.text.toString(),
            bday.text.toString(),
            getGender(),
            memo.text.toString()
        )
        contactRepository.addContact(newContact)
        showToast("저장이 완료 되었습니다.")
    }

    fun setIntent() {
        val intent = Intent()
        setResult(RESULT_OK, intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_add)

        name = findViewById<EditText>(R.id.contactName)
        tel = findViewById<EditText>(R.id.contactTel)
        mail = findViewById<EditText>(R.id.contactMail)
        bday = findViewById<TextView>(R.id.contactBirthDay)
        genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        memo = findViewById<EditText>(R.id.contactMemo)
        val editTextList = findViewById<LinearLayout>(R.id.editTextList)
        val showDetail = findViewById<TextView>(R.id.more)
        val cancelBtn = findViewById<MaterialButton>(R.id.cancelBtn)
        val submitBtn = findViewById<MaterialButton>(R.id.submitBtn)

        val essentialInputViews = listOf(name, tel)
        val views = listOf(name, tel, mail, bday, genderRadioGroup, memo)

        showDetail.setOnClickListener {
            extendEditTextList(editTextList, R.dimen.contact_list_height_detail)
            toggleViewVisibility(showDetail)
        }

        bday.setOnClickListener {
            startCalenderDialog(it as TextView)
        }

        cancelBtn.setOnClickListener {
            cancelAddContact()
        }

        submitBtn.setOnClickListener {
            if (isValidContact(essentialInputViews)) {
                submitContact()
                setIntent()
                finish()
            }
        }

        this.onBackPressedDispatcher.addCallback(this) {
            if (isWriting(views)) {
                showExitConfirmDialog()
            } else {
                finish()
            }
        }

    }
}
