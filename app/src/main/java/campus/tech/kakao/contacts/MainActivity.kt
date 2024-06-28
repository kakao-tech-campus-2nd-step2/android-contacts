package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEdit: EditText = findViewById(R.id.NameEdit)
        val phoneNumberEdit: EditText = findViewById(R.id.PhoneNumberEdit)
        val moreText: TextView = findViewById(R.id.MoreText)
        val birthEdit: EditText = findViewById(R.id.BirthEdit)
        val memoEdit: EditText = findViewById(R.id.MemoEdit)
        val genderArea: LinearLayoutCompat = findViewById(R.id.GenderArea)
        val saveText: TextView = findViewById(R.id.SaveText)
        val cancelText: TextView = findViewById(R.id.CancleText)

        moreText.setOnClickListener {
            moreText.visibility = View.GONE
            birthEdit.visibility = View.VISIBLE
            memoEdit.visibility = View.VISIBLE
            genderArea.visibility = View.VISIBLE
        }

        saveText.setOnClickListener {
            val name = nameEdit.text.toString().trim()
            val phoneNumber = phoneNumberEdit.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this@MainActivity, "이름은 필수값입니다.", Toast.LENGTH_SHORT).show()
            } else if (phoneNumber.isEmpty()) {
                Toast.makeText(this@MainActivity, "전화번호는 필수값입니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "저장되었습니다.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ListActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("phoneNumber", phoneNumber)
                }
                startActivity(intent)
                finish()  // MainActivity를 종료하여 ListActivity로 돌아감
            }
        }

        cancelText.setOnClickListener {
            Toast.makeText(this@MainActivity, "취소되었습니다.", Toast.LENGTH_SHORT).show()
            finish()  // MainActivity를 종료하여 ListActivity로 돌아감
        }
    }
}
