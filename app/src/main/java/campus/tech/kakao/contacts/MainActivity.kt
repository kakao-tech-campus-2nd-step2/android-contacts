package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var contactList: LinearLayout
    private lateinit var emptyText: TextView
    private lateinit var addContactLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactList = findViewById(R.id.contact_list)
        emptyText = findViewById(R.id.empty_text)
        val fab = findViewById<TextView>(R.id.fab)

        // ActivityResultLauncher 초기화
        addContactLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val name = data.getStringExtra("name") ?: ""
                    val phone = data.getStringExtra("phone") ?: ""
                    val email = data.getStringExtra("email") ?: ""
                    val birthdate = data.getStringExtra("birthdate") ?: ""
                    val gender = data.getStringExtra("gender") ?: ""
                    val notes = data.getStringExtra("notes") ?: ""

                    // 연락처가 추가되면 가운데 텍스트를 숨김
                    emptyText.visibility = View.GONE

                    val contactView = LayoutInflater.from(this).inflate(R.layout.contact_item, contactList, false) as CardView

                    contactView.findViewById<TextView>(R.id.contact_name).text = name
                    contactView.findViewById<TextView>(R.id.contact_icon).text = name.first().toString()

                    contactView.setOnClickListener {
                        val detailIntent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                            putExtra("name", name)
                            putExtra("phone", phone)
                            putExtra("email", email)
                            putExtra("birthdate", birthdate)
                            putExtra("gender", gender)
                            putExtra("notes", notes)
                        }
                        startActivity(detailIntent)
                    }

                    contactList.addView(contactView)
                }
            }
        }

        // '+' 버튼 클릭 이벤트 처리
        fab.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            addContactLauncher.launch(intent)
        }
    }
}
