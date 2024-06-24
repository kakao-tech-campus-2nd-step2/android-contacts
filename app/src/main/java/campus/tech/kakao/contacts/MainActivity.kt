package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var phoneNumEditText: EditText
    lateinit var cancelBtn: Button
    lateinit var saveBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setOnClickListeners()
    }

    private fun initViews() {
        nameEditText = findViewById(R.id.name_edit_text)
        phoneNumEditText = findViewById(R.id.phone_num_edit_text)
        cancelBtn = findViewById(R.id.cancel_btn)
        saveBtn = findViewById(R.id.save_btn)
    }

    private fun setOnClickListeners() {
        setOnClickListenerOfSaveBtn()
    }

    private fun setOnClickListenerOfSaveBtn() {
        saveBtn.setOnClickListener {
            if (nameEditText.text.isEmpty()) {
                Toast.makeText(this, "이름은 필수 값 입니다.", Toast.LENGTH_LONG).show()
            } else if (phoneNumEditText.text.isEmpty()) {
                Toast.makeText(this, "전화 번호는 필수 값 입니다.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
