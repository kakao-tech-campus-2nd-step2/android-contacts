package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var phoneNumEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var birthdayEditText: EditText
    lateinit var genderLayout: ConstraintLayout
    lateinit var memoEditText: EditText
    lateinit var cancelBtn: Button
    lateinit var saveBtn: Button
    lateinit var seeMoreLayout:LinearLayoutCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setOnClickListeners()
    }

    private fun initViews() {
        nameEditText = findViewById(R.id.name_edit_text)
        phoneNumEditText = findViewById(R.id.phone_num_edit_text)
        emailEditText = findViewById(R.id.email_edit_text)
        birthdayEditText = findViewById(R.id.birthday_edit_text)
        genderLayout = findViewById(R.id.gender_layout)
        memoEditText = findViewById(R.id.memo_edit_text)
        cancelBtn = findViewById(R.id.cancel_btn)
        saveBtn = findViewById(R.id.save_btn)
        seeMoreLayout = findViewById(R.id.see_more_layout)
    }

    private fun setOnClickListeners() {
        setOnClickListenerOfSaveBtn()
        setOnClickListenerOfSeeMoreLayout()
    }

    private fun setOnClickListenerOfSaveBtn() {
        saveBtn.setOnClickListener {
            if (nameEditText.text.isEmpty()) {
                Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_LONG).show()
            } else if (phoneNumEditText.text.isEmpty()) {
                Toast.makeText(this, "전화 번호는 필수 값 입니다.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "저장이 완료 되었습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setOnClickListenerOfSeeMoreLayout() {
        seeMoreLayout.setOnClickListener {
            seeMoreLayout.visibility = View.GONE
            birthdayEditText.visibility = View.VISIBLE
            genderLayout.visibility = View.VISIBLE
            memoEditText.visibility = View.VISIBLE
        }
    }
}
