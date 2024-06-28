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
    private lateinit var contactsButton2: Button
    private lateinit var contactsButton3: Button
    private lateinit var contactsButton4: Button
    private lateinit var contactsButton5: Button
    private lateinit var contactsButton6: Button
    private lateinit var addButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {

        getSharedPreferences("userInformation", Context.MODE_PRIVATE).edit().clear()
        getSharedPreferences("userInformation", Context.MODE_PRIVATE).edit().apply()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_list)


        contactsButton1 = findViewById(R.id.contactsButton1)
        contactsButton2 = findViewById(R.id.contactsButton2)
        contactsButton3 = findViewById(R.id.contactsButton3)
        contactsButton4 = findViewById(R.id.contactsButton4)
        contactsButton5 = findViewById(R.id.contactsButton5)
        contactsButton6 = findViewById(R.id.contactsButton6)

        addButton = findViewById(R.id.addButton)


        addButton.setOnClickListener {
            Intent(this, MainActivity::class.java).let{
                startActivity(it)
            }
        }
        contactsButton1.setOnClickListener {
            val count = 1
            Intent(this, contactsInfo::class.java).let{
                intent.putExtra("count", count)
                startActivity(it)
            }
        }
        contactsButton2.setOnClickListener {
            val count = 2
            Intent(this, contactsInfo::class.java).let{
                intent.putExtra("count", count)
                startActivity(it)
            }
        }
        contactsButton3.setOnClickListener {
            val count = 3
            Intent(this, contactsInfo::class.java).let{
                intent.putExtra("count", count)
                startActivity(it)
            }
        }
        contactsButton4.setOnClickListener {
            val count = 4
            Intent(this, contactsInfo::class.java).let{
                intent.putExtra("count", count)
                startActivity(it)
            }
        }
        contactsButton5.setOnClickListener {
            val count = 5
            Intent(this, contactsInfo::class.java).let{
                intent.putExtra("count", count)
                startActivity(it)
            }
        }
        contactsButton6.setOnClickListener {
            val count = 6
            Intent(this, contactsInfo::class.java).let{
                intent.putExtra("count", count)
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
            contactsButton1.text = getString("name1", "")
            contactsButton2.text = getString("name2", "")
            contactsButton3.text = getString("name3", "")
            contactsButton4.text = getString("name4", "")
            contactsButton5.text = getString("name5", "")
            contactsButton6.text = getString("name6", "")

        }
    }
}