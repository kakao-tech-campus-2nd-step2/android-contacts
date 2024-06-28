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
    private lateinit var viewFields: Array<View>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val contIdx = intent.getIntExtra("contIdx", -1)

        inputFields = arrayOf(
            findViewById(R.id.contInputName),
            findViewById(R.id.contInputPhone),
            findViewById(R.id.contInputMail),
            findViewById(R.id.contInputBirth),
            findViewById(R.id.contInputGender),
            findViewById(R.id.contInputMemo)
        )

        viewFields = arrayOf(
            findViewById(R.id._contMail),
            findViewById(R.id._contBirth),
            findViewById(R.id._contGender),
            findViewById(R.id._contMemo)
        )

        val infos: Array<String> = arrayOf(
            "name", "phoneNumber", "mail", "birth", "gender", "memo"
        )

        val contact: Contact = Contacts.getContact(contIdx)

        for (i in 0..5) {
            val value = contact[infos[i]]
            if (value.isNotEmpty()) {
                inputFields[i].setText(value)
                if (i >= 2) {
                    viewFields[i - 2].visibility = View.VISIBLE
                }
            }
        }
    }
}