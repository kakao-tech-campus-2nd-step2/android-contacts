package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    //    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
//        val inputMethodManager: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
//        return super.dispatchTouchEvent(ev)
//    }
    fun startCalenderDialog(textView: TextView) {
        val datePickerDialog = DatePickerDialog(this)
        datePickerDialog.datePicker.init(
            2000, 0, 1
        ) { _, year, month, dayOfMonth ->
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

    fun cancelContact() {
        // TODO: implement cancel workflow
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

    fun submitContact() {
        // TODO: implement submit workflow
        showToast("저장이 완료 되었습니다.")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.contactName)
        val tel = findViewById<EditText>(R.id.contactTel)
        val mail = findViewById<EditText>(R.id.contactMail)
        val editTextList = findViewById<LinearLayout>(R.id.editTextList)
        val showDetail = findViewById<LinearLayout>(R.id.more)
        val bday = findViewById<TextView>(R.id.contactBirthDay)
        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        val memo = findViewById<EditText>(R.id.contactMemo)
        val cancelBtn = findViewById<MaterialButton>(R.id.cancelBtn)
        val submitBtn = findViewById<MaterialButton>(R.id.submitBtn)

        showDetail.setOnClickListener {
            extendEditTextList(editTextList, R.dimen.contact_list_height_detail)
            toggleViewVisibility(showDetail)
        }

        bday.setOnClickListener {
            startCalenderDialog(it as TextView)
        }

        cancelBtn.setOnClickListener {
            cancelContact()
        }

        submitBtn.setOnClickListener {
            if (isValidContact(listOf(name, tel))) {
                submitContact()
            }
        }

    }
}
