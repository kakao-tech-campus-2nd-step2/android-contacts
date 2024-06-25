package campus.tech.kakao.contacts

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactRegisterActivity : AppCompatActivity() {
	lateinit var nameInputView: EditText
	lateinit var phoneInputView: EditText
	lateinit var emailInputView: EditText
	lateinit var birthdayInputView: EditText
	lateinit var femaleRadioButton: RadioButton
	lateinit var maleRadioButton: RadioButton
	lateinit var memoInputView: EditText

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_contact_register)

		setMoreInfoVisibility()

		nameInputView = findViewById(R.id.contactName)
		phoneInputView = findViewById(R.id.contactPhone)
		emailInputView = findViewById(R.id.contactMail)
		birthdayInputView = findViewById(R.id.contactBirthday)
		femaleRadioButton = findViewById(R.id.femaleRadioButton)
		maleRadioButton = findViewById(R.id.maleRadioButton)
		memoInputView = findViewById(R.id.contactMemo)

		findViewById<Button>(R.id.cancelButton).setOnClickListener {
			clearInputFields()
			Toast.makeText(this@ContactRegisterActivity, "취소되었습니다.", Toast.LENGTH_SHORT).show()
		}
	}

	private fun setMoreInfoVisibility() {
		findViewById<LinearLayout>(R.id.moreInfoButton).setOnClickListener { moreInfoButton ->
			findViewById<LinearLayout>(R.id.moreInfoLayout).visibility = View.VISIBLE
			moreInfoButton.visibility = View.GONE
		}
	}

	private fun clearInputFields() {
		nameInputView.text.clear()
		phoneInputView.text.clear()
		emailInputView.text.clear()
		birthdayInputView.text.clear()
		femaleRadioButton.isChecked = false
		maleRadioButton.isChecked = false
		memoInputView.text.clear()
	}
}