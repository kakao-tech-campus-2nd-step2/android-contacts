package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        val cancelButton: Button = findViewById(R.id.cancel)
        val saveButton: Button = findViewById(R.id.save)

        cancelButton.setOnClickListener {
            Toast.makeText(this, "취소 되었습니다", Toast.LENGTH_SHORT).show()
            val returnIntent: Intent = Intent()
            setResult(RESULT_CANCELED, returnIntent)
            finish()
        }

        saveButton.setOnClickListener {
            Toast.makeText(this, "저장이 완료 되었습니다", Toast.LENGTH_SHORT).show()
            val returnIntent: Intent = Intent()
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}