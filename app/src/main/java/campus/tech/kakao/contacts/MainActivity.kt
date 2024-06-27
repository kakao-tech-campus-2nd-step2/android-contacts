package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameField: EditText = findViewById(R.id.name_field)
        val phoneField: EditText = findViewById(R.id.phone_field)
        val emailField: EditText = findViewById(R.id.email_field)
        val saveButton: Button = findViewById(R.id.save_button)
        val cancelButton: Button = findViewById(R.id.cancel_button)
        val moreButton: LinearLayout = findViewById(R.id.more_button)
        val additionalFields: LinearLayout = findViewById(R.id.additional_fields)


        moreButton.setOnClickListener {
            moreButton.visibility = View.GONE
            additionalFields.visibility = View.VISIBLE
        }

        saveButton.setOnClickListener {
            val name = nameField.text.toString()
            val phone = phoneField.text.toString()
            val email = emailField.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_name_is_essential), Toast.LENGTH_SHORT).show()
            } else if (phone.isEmpty()) {
                Toast.makeText(this,
                    getString(R.string.toast_phone_number_is_essential), Toast.LENGTH_SHORT).show()
            } else {
                var message = getString(R.string.toast_saved)
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
        cancelButton.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_canceled), Toast.LENGTH_SHORT).show()
        }
    }
}
