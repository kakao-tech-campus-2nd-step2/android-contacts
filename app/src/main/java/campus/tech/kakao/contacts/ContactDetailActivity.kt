package campus.tech.kakao.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import campus.tech.kakao.contacts.databinding.ActivityContactDetailBinding

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contact = intent.getParcelableExtra<Contact>("CONTACT")
        contact?.let {
            binding.nameOfContact.text = it.name
            binding.phoneNumberOfContact.text = it.phoneNumber
            binding.emailAddressOfContact.text = it.emailAddress
            binding.birthdayOfContact.text = it.birthday
            binding.genderOfContact.text = it.gender
            binding.memoOfContact.text = it.memo
        }
    }
}
