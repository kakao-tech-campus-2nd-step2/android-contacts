package campus.tech.kakao.contacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class contactsList : AppCompatActivity() {

    private lateinit var contactsButton1: Button
    private lateinit var addButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_list)

        contactsButton1 = findViewById(R.id.contactsButton1)
        addButton = findViewById(R.id.addButton)


        addButton.setOnClickListener {
            Intent(this, MainActivity::class.java).let{
                startActivity(it)
            }
        }
        contactsButton1.setOnClickListener {
            Intent(this, contactsInfo::class.java).let{
                startActivity(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        with(getSharedPreferences("userInformation", Context.MODE_PRIVATE)){
            contactsButton1.text = getString("name", "미정")
        }
    }
}