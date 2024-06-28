package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    private lateinit var inputFields: Array<TextView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val contIdx = intent.getIntExtra("contIdx", -1)

        inputFields = arrayOf(
            findViewById(R.id.contInputName),
            findViewById(R.id.contInputPhone)
        )

        val contact: Contact = Contacts.getContact(contIdx)

        inputFields[0].setText(contact.name)
        inputFields[1].setText(contact.phoneNumber)
    }
}