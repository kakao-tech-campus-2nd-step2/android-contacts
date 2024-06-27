package campus.tech.kakao.contacts

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ButtonBarLayout

class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var mail: EditText
    private lateinit var extraBtn: LinearLayout
    private lateinit var birth: EditText
    private lateinit var genderRadio: RadioGroup
    private lateinit var memo: EditText
    private lateinit var cancel: Button
    private lateinit var save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()

        extraBtn.setOnClickListener {
            birth.visibility = View.VISIBLE
            genderRadio.visibility = View.VISIBLE
            memo.visibility = View.VISIBLE
            extraBtn.visibility = View.GONE
        }

        cancel.setOnClickListener {
            name.text = null;
            phoneNumber.text = null;
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_LONG).show()
        }

        save.setOnClickListener {
            if (name.getText().isEmpty()) {
                Toast.makeText(this, "이름을 반드시 적어야 합니다", Toast.LENGTH_LONG).show()
            }
            else if (phoneNumber.getText().isEmpty()) {
                Toast.makeText(this, "전화번호를 반드시 적어야 합니다", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun findViews() {
        name = findViewById(R.id.name)
        phoneNumber = findViewById(R.id.phoneNumber)
        mail = findViewById(R.id.mail)
        extraBtn = findViewById(R.id.extraBtn)
        birth = findViewById(R.id.birth)
        genderRadio = findViewById(R.id.genderRadio)
        memo = findViewById(R.id.memo)
        cancel = findViewById(R.id.cancel)
        save = findViewById(R.id.save)
    }
}
