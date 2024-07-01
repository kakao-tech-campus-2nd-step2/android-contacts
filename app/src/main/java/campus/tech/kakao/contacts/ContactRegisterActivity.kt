package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactRegisterActivity : AppCompatActivity() {
	private lateinit var nameInputView: EditText
	private lateinit var phoneInputView: EditText
	private lateinit var emailInputView: EditText
	private lateinit var birthdayInputView: EditText
	private lateinit var femaleRadioButton: RadioButton
	private lateinit var maleRadioButton: RadioButton
	private lateinit var memoInputView: EditText

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
			Toast.makeText(this@ContactRegisterActivity, "취소되었습니다.", Toast.LENGTH_SHORT).show()
			finish()
		}

		findViewById<Button>(R.id.saveButton).setOnClickListener {
			if (checkRequiredFields()) {
				val intent = Intent()
				intent.putExtra("contactData", saveContact())
				setResult(RESULT_OK, intent)
				Toast.makeText(this@ContactRegisterActivity, "저장되었습니다.", Toast.LENGTH_SHORT).show()
				finish()
			}
		}

		onBackPressedDispatcher.addCallback(backPressedCallback)
	}

	private val backPressedCallback = object : OnBackPressedCallback(true) {
		override fun handleOnBackPressed() {
			val dialogBuilder = AlertDialog.Builder(this@ContactRegisterActivity)
			dialogBuilder.setMessage("작성중인 내용이 있습니다. 정말 나가시겠습니까?")
			dialogBuilder.setNegativeButton("작성하기", null)
			dialogBuilder.setPositiveButton("나가기") { dialog, which ->
				Toast.makeText(this@ContactRegisterActivity, "취소되었습니다.", Toast.LENGTH_SHORT).show()
				finish()
			}
			dialogBuilder.show()
		}
	}

	private fun setMoreInfoVisibility() {
		findViewById<LinearLayout>(R.id.moreInfoButton).setOnClickListener { moreInfoButton ->
			findViewById<LinearLayout>(R.id.moreInfoLayout).visibility = View.VISIBLE
			moreInfoButton.visibility = View.GONE
		}
	}

	private fun checkRequiredFields(): Boolean {
		return if (nameInputView.text.isEmpty()) {
			Toast.makeText(this@ContactRegisterActivity, "이름은 필수 입력사항입니다.", Toast.LENGTH_SHORT)
				.show()
			false
		} else if (phoneInputView.text.isEmpty()) {
			Toast.makeText(this@ContactRegisterActivity, "전화번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT)
				.show()
			false
		} else true
	}

	private fun saveContact(): ContactData = ContactData(
		name = nameInputView.text.toString(),
		phone = phoneInputView.text.toString(),
		email = when {
			emailInputView.text.isEmpty() -> null
			else -> emailInputView.text.toString()
		},
		birthday = when {
			birthdayInputView.text.isEmpty() -> null
			else -> birthdayInputView.text.toString()
		},
		isFemale = when {
			femaleRadioButton.isChecked -> true
			maleRadioButton.isChecked -> false
			else -> null
		},
		memo = when {
			memoInputView.text.isEmpty() -> null
			else -> memoInputView.text.toString()
		}
	)
}