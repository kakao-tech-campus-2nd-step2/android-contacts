package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.name)
        val phone = findViewById<EditText>(R.id.phone)
        val mail = findViewById<EditText>(R.id.mail)
        val save = findViewById<Button>(R.id.save)
        val cancel = findViewById<Button>(R.id.cancel)
        val more = findViewById<LinearLayoutCompat>(R.id.more)
        val birthday = findViewById<TextView>(R.id.birthday)
        val moreLayout = findViewById<LinearLayoutCompat>(R.id.moreLayout)
        val birthdayLayout = findViewById<ConstraintLayout>(R.id.birthdayLayout)
        val genderLayout = findViewById<ConstraintLayout>(R.id.genderLayout)
        val female = findViewById<RadioButton>(R.id.female)
        val male = findViewById<RadioButton>(R.id.male)
        val memo = findViewById<EditText>(R.id.memo)

        name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        phone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        mail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
        cancel.setOnClickListener {
            Toast.makeText(this@MainActivity, "취소 되었습니다.", Toast.LENGTH_SHORT).show()
        }

        save.setOnClickListener {
            val writename = name.text.toString()
            val writenumber = phone.text.toString()

            var namecomplete = false
            var numbercomplete = false

            if (writename.isBlank()) {
                Toast.makeText(this@MainActivity, "이름은 필수 입력 값입니다.", Toast.LENGTH_SHORT).show()
            } else {
                namecomplete = true
            }

            if (writenumber.isBlank()) {
                Toast.makeText(this@MainActivity, "전화번호는 필수 입력 값입니다.", Toast.LENGTH_SHORT).show()
            } else if (!writenumber.all { it.isDigit() }) {
                Toast.makeText(this@MainActivity, "전화번호에는 숫자만 입력할 수 있습니다.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                numbercomplete = true
            }

            if (namecomplete == true && numbercomplete == true) {
                Toast.makeText(this@MainActivity, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
            }
        }

        more.setOnClickListener {
            moreLayout.visibility = View.GONE
            birthdayLayout.visibility = View.VISIBLE
            genderLayout.visibility = View.VISIBLE
            memo.visibility = View.VISIBLE

            birthdayLayout.setOnClickListener {
                showDatePicker()
            }
        }
    }
    fun showDatePicker() {
        val birthday = findViewById<TextView>(R.id.birthday)
        val calendar = Calendar.getInstance()
        val data = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            birthday.text = "${year}.${month}.${day}"
        }

        DatePickerDialog(this, data, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)).show()
    }
}
