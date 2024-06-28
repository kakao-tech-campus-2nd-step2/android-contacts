package campus.tech.kakao.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import campus.tech.kakao.contacts.databinding.ActivityDetailContactBinding

class DetailContact : AppCompatActivity() {
    private lateinit var binding: ActivityDetailContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent로부터 데이터를 받아와서 UI에 설정
        val name = intent.getStringExtra("name")
        val phoneNumber = intent.getStringExtra("phoneNumber")

        binding.Name.text = name
        binding.TelePhone.text = phoneNumber
    }
}
