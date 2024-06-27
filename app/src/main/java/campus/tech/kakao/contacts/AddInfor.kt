package campus.tech.kakao.contacts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class AddInfor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_infor)
        val forMoreInformation = findViewById<Button>(R.id.forMoreInformation)
        val addInfor = findViewById<LinearLayout>(R.id.addInfor)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val inputName = findViewById<EditText>(R.id.inputName)
        val inputPhoneNumber = findViewById<EditText>(R.id.inputPhoneNumber)
    }
}