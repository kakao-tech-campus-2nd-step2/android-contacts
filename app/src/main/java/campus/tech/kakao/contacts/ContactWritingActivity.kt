package campus.tech.kakao.contacts

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import java.util.Calendar


class ContactWritingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_writing)


        val name = findViewById<EditText>(R.id.name)
        val phone = findViewById<EditText>(R.id.phone)
        val mail = findViewById<EditText>(R.id.mail)
        val moreBtn = findViewById<LinearLayoutCompat>(R.id.moreBtn)
        val moreInfo = findViewById<LinearLayoutCompat>(R.id.moreInfo)
        val birthday = findViewById<TextView>(R.id.birthday)
        val gender = findViewById<RadioGroup>(R.id.gender)
        val memo = findViewById<EditText>(R.id.memo)
        val save = findViewById<TextView>(R.id.save)
        val cancel = findViewById<TextView>(R.id.cancel)

        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        moreInfo.visibility = View.INVISIBLE

        save.setOnClickListener {
            if (name.text.isEmpty()) {
                val toast = Toast.makeText(this, "이름은 필수값입니다", Toast.LENGTH_SHORT)
                toast.show()
                name.requestFocus()
                inputMethodManager.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT)
            } else if (phone.text.isEmpty()) {
                val toast = Toast.makeText(this, "전화번호는 필수값입니다", Toast.LENGTH_SHORT)
                toast.show()
                phone.requestFocus()
                inputMethodManager.showSoftInput(phone, InputMethodManager.SHOW_IMPLICIT)
            } else {
                val toast = Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT)
                toast.show()

                val intent = Intent(this, ContactMainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                intent.putExtra("name", name.text.toString())
                intent.putExtra("phone", phone.text.toString())
                //intent.putExtra("mail", mail.text)
                //intent.putExtra("birthday", birthday.text)
                //intent.putExtra("gender", name.text) //gender 선택한 값으로
                //intent.putExtra("memo", memo.text)
                startActivity(intent)
            }
        }

        cancel.setOnClickListener {
            val toast = Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT)
            toast.show()
        }

        moreBtn.setOnClickListener {
            moreInfo.visibility = View.VISIBLE
            moreBtn.visibility = View.INVISIBLE
        }

        birthday.setOnClickListener {
            val calendar = Calendar.getInstance()    //캘린더뷰 만들기
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    birthday.text = "" + year + "." + (month + 1) + "." + dayOfMonth
                }
            DatePickerDialog(
                this, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}
