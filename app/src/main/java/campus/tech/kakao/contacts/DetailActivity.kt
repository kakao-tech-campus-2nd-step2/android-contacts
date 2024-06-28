package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val phoneNum = intent.getStringExtra("phoneNum")
        val name = intent.getStringExtra("name")


        val nameTextView: TextView = findViewById(R.id.detail_name)
        val phoneNumTextView: TextView = findViewById(R.id.detail_phoneNum)

        phoneNumTextView.text = phoneNum
        nameTextView.text = name
    }
}
