package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactListActivity : AppCompatActivity() {
    
    lateinit var plusbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUI()
        goToAddContact()
    }

    private fun setUI(){
        plusbtn = findViewById(R.id.btn_plus)
    }

    fun goToAddContact() {
        plusbtn.setOnClickListener {
            val intent = Intent(this, SaveContactActivity::class.java)
            startActivity(intent)
        }
    }
}