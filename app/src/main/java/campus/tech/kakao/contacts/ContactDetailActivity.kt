package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import campus.tech.kakao.contacts.databinding.ActivityContactDetailBinding

class ContactDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityContactDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        display()
    }

    private fun display(){
        binding.nameDetail.text = intent.getStringExtra(NAME)
        binding.phoneDetail.text = intent.getStringExtra(PHONE)

        val email = intent.getStringExtra(EMAIL)
        if (email != null) {
            binding.emailDetail.text = email
        } else {
            binding.textEmail.visibility = View.GONE
        }

        val birth = intent.getStringExtra(BIRTH)
        if (birth != null) {
            binding.birthDetail.text = birth
        } else {
            binding.textBirth.visibility = View.GONE
        }

        val gender = intent.getStringExtra(GENDER_TYPE)
        if (gender != null) {
            binding.genderDetail.text = gender
        } else {
            binding.textGender.visibility = View.GONE
        }

        val note = intent.getStringExtra(NOTE)
        if (note != null) {
            binding.noteDetail.text = note
        } else {
            binding.textNote.visibility = View.GONE
        }
    }
}