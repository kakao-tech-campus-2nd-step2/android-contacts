package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListActivity : AppCompatActivity() {
    lateinit var plusButton : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val intent = getIntent()
        val contactList = mutableListOf<Contact>()
        val contact = Contact(
            intent.getStringExtra("name").toString(),
            intent.getStringExtra("phoneNumber").toString(),
            intent.getStringExtra("mail").toString(),
            intent.getStringExtra("birthday").toString(),
            intent.getStringExtra("gender").toString(),
            intent.getStringExtra("memo").toString())
        contactList.add(contact)

        val plusButton = findViewById<TextView>(R.id.plus_item_button)
        plusButton.setOnClickListener{
            moveToAddContact()
        }
    }

    fun moveToAddContact(){
        val intent = Intent(this@ListActivity, MainActivity::class.java)
        startActivity(intent)
    }
}

class Contact(var name : String, var phoneNumber : String, var mail : String, var birthday : String, var gender : String, var memo : String){
    fun existsMail(): Boolean {
        return mail != ""
    }

    fun existsBirthday(): Boolean {
        return birthday != ""
    }

    fun existsGender(): Boolean {
        return gender != ""
    }

    fun existsMemo(): Boolean {
        return memo != ""
    }
}