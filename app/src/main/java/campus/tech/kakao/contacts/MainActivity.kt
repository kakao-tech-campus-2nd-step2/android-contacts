package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var tel: EditText
    private lateinit var mail: EditText
    private lateinit var birth: EditText
    private lateinit var gender: EditText
    private lateinit var genderRadio: LinearLayout
    private lateinit var memo: EditText
    private lateinit var viewMore: TextView
    private lateinit var cancel: Button
    private lateinit var save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()
        setUpListeners()
    }

    private fun initializeView() {
        name = findViewById(R.id.name)
        tel = findViewById(R.id.tel)
        mail = findViewById(R.id.mail)
        birth = findViewById(R.id.birth)
        gender = findViewById(R.id.gender)
        genderRadio = findViewById(R.id.genderRadio)
        memo = findViewById(R.id.memo)
        viewMore = findViewById(R.id.viewMore)
        cancel = findViewById(R.id.cancel)
        save = findViewById(R.id.save)
    }


    private fun setUpListeners() {
        viewMore.setOnClickListener {
            expandView()
        }
        cancel.setOnClickListener {
            Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_SHORT).show()
        }
        save.setOnClickListener {
            if (checkValide()) {
                Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        birth.setOnClickListener {
            showCalendar()
        }
    }

    private fun expandView() {
        birth.visibility = View.VISIBLE
        gender.visibility = View.VISIBLE
        genderRadio.visibility = View.VISIBLE
        memo.visibility = View.VISIBLE
        viewMore.visibility = View.INVISIBLE
    }

    private fun checkValide(): Boolean {
        if (name.text.isEmpty()) {
            Toast.makeText(this, "이름은 필수 입력값입니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        else if (tel.text.isEmpty()) {
            Toast.makeText(this, "전화번호는 필수 입력값입니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        else {
            return true
        }
    }

    private fun showCalendar() {
        var dateString = ""
        val cal = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            dateString = "${year}.${month+1}.${dayOfMonth}"
            birth.setText(dateString)

        }
        DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
    }



}
