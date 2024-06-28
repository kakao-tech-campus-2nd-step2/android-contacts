package campus.tech.kakao.contacts

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class contactsInfo : AppCompatActivity() {

    private lateinit var personName: TextView
    private lateinit var personNumber: TextView
    private lateinit var personMail: TextView
    private lateinit var personBirthday: TextView
    private lateinit var personGender: TextView
    private lateinit var personMemo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_info)

        personName = findViewById(R.id.personName)
        personNumber = findViewById(R.id.personNumber)
        personMail = findViewById(R.id.personMail)
        personBirthday = findViewById(R.id.personBirthday)
        personGender = findViewById(R.id.personGender)
        personMemo = findViewById(R.id.personMemo)
        getData()
    }

    private fun getData() {
        with(getSharedPreferences("userInformation", Context.MODE_PRIVATE)){
            personName.text = getString("name", "미정")
            personNumber.text = getString("number", "미정")
            personMail.text = getString("mail", "미정")
            personBirthday.text = getString("birthday", "미정")
            personGender.text = getString("gender", "미정")
            personMemo.text = getString("memo", "미정")

        }
    }
}