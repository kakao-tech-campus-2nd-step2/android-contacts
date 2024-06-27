package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat

class AddContactActivity : AppCompatActivity() {

    val contactManager = ContactManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

            val name = findViewById<EditText>(R.id.name)
            val phoneNumber = findViewById<EditText>(R.id.phoneNumber)
            val email = findViewById<EditText>(R.id.email)
            val birthDay = findViewById<EditText>(R.id.birthDay)
            val gender = findViewById<RadioGroup>(R.id.gender)
            val memo = findViewById<EditText>(R.id.memo)

            val birthDayLayout = findViewById<LinearLayoutCompat>(R.id.birthDayLayout)
            val genderLayout = findViewById<LinearLayoutCompat>(R.id.genderLayout)
            val memoLayout = findViewById<LinearLayoutCompat>(R.id.memoLayout)

            val more = findViewById<LinearLayoutCompat>(R.id.more)
            val save = findViewById<TextView>(R.id.save)
            val cancle = findViewById<TextView>(R.id.cancle)

            more.setOnClickListener {
                birthDayLayout.visibility = View.VISIBLE
                genderLayout.visibility = View.VISIBLE
                memoLayout.visibility = View.VISIBLE
                more.visibility = View.GONE
            }

            cancle.setOnClickListener {
                contactManager.showToast(this@AddContactActivity, "취소되었습니다.")
            }

            save.setOnClickListener {
                if(contactManager.checkIsFilled(name.getText().toString())&&contactManager.checkIsFilled(phoneNumber.getText().toString()))
                {
                    contactManager.showToast(this@AddContactActivity, "저장되었습니다.")
                } else {
                    contactManager.showToast(this@AddContactActivity, "이름과 번호 입력은 필수입니다.")
                }
            }
    }
}