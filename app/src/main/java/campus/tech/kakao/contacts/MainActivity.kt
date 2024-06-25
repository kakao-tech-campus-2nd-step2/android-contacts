package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSave: TextView = findViewById<TextView>(R.id.btnSave)
        val btnCancel: TextView = findViewById<TextView>(R.id.btnCancel)
        val inputName: EditText = findViewById<EditText>(R.id.inputName)
        val inputPhoneNumber: EditText = findViewById<EditText>(R.id.inputPhoneNumber)
        
        btnSave.setOnClickListener {
            if (inputName.text.isEmpty() || inputPhoneNumber.text.isEmpty()){
                Toast.makeText(this, "이름 또는 전화번호를 입력하세요", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
            }
        }

        btnCancel.setOnClickListener {
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
