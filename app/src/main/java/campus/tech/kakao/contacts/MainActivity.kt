package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val contactList: MutableList<ContactInfo> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private fun receivedInfor(intent: Intent?) {
        intent?.let {
            val addName = it.getStringExtra("inforname") ?: "Null"
            val addPhoneNumber = it.getStringExtra("infornumber") ?: "null"
            val addEmail = it.getStringExtra("inforEmail") ?: ""
            val addBirth = it.getStringExtra("inforBirth") ?: ""
            val addMemo = it.getStringExtra("inforMemo") ?: ""
            val addGender = it.getStringExtra("inforGender") ?: ""

            val contactInfo =
                ContactInfo(addName, addPhoneNumber, addEmail, addBirth, addMemo, addGender)
            contactList.add(contactInfo)
        }
    }
}
data class ContactInfo(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val birth: String,
    val memo: String,
    val gender: String
)