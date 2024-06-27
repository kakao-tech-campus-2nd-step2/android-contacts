package campus.tech.kakao.contacts

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ButtonBarLayout

class MainActivity : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var phoneNumber: EditText
    lateinit var cancel: Button
    lateinit var save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()

        cancel.setOnClickListener {
            name.setText(null);
            phoneNumber.setText(null);
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
        cancel = findViewById(R.id.cancel)
        save = findViewById(R.id.save)
    }
}
