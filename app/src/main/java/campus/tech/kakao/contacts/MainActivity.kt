package campus.tech.kakao.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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