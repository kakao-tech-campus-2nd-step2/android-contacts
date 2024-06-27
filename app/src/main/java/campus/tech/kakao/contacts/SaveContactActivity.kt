package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SaveContactActivity : AppCompatActivity() {

    lateinit var cancel: Button
    lateinit var save: Button
    lateinit var more: Button
    lateinit var more_layout: LinearLayout

    lateinit var date_birth : EditText
    lateinit var dateString : String
    lateinit var imm : InputMethodManager

    lateinit var name : EditText
    lateinit var phone : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUI()
        showMoreEditTexts()
        showDatePicker()
        onCancelClicked()
        onSaveClicked()
    }

    fun setUI(){
        more = findViewById(R.id.btn_more)
        more_layout = findViewById(R.id.addfield)

        date_birth = findViewById(R.id.edit_birth)
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        cancel = findViewById(R.id.btn_cancel)
        save = findViewById(R.id.btn_save)

        name = findViewById(R.id.edit_name)
        phone = findViewById(R.id.edit_phone)
    }

    fun showMoreEditTexts(){
        more.setOnClickListener {
            more.visibility = if(more.visibility == View.VISIBLE){
                View.GONE
            } else {View.VISIBLE}
            more_layout.visibility = if(more_layout.visibility == View.GONE){
                View.VISIBLE
            } else {View.GONE}
        }
    }

    fun showDatePicker(){
        date_birth.setOnClickListener {
            val cal = Calendar.getInstance()
            cal.set(2000, Calendar.JANUARY, 1)

            imm.hideSoftInputFromWindow(
                currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )

            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                dateString= "${year}.${month+1}.${dayOfMonth}"
                date_birth.setText(dateString)
            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this@SaveContactActivity, message, Toast.LENGTH_SHORT).show()
    }

    fun onCancelClicked(){
        cancel.setOnClickListener {
            showToast("취소 되었습니다")
        }
    }

    fun onSaveClicked(){
        save.setOnClickListener {
            if (name.text.isEmpty()){
                showToast("이름은 필수 입력 항목입니다")
                name.requestFocus()
            }
            else if (phone.text.isEmpty()){
                showToast("전화번호는 필수 입력 항목입니다")
                phone.requestFocus()
            }
            else {showToast("저장이 완료 되었습니다")}
        }
    }
}
