package campus.tech.kakao.contacts

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactInfoActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_contact_info)

		when (Build.VERSION.SDK_INT) {
			in 33..Int.MAX_VALUE ->
				intent.getSerializableExtra("contactData", ContactData::class.java)
			else ->
				intent.getSerializableExtra("contactData") as ContactData?
		}?.let {
			findViewById<TextView>(R.id.contactName).text = it.name
			findViewById<TextView>(R.id.contactPhone).text = it.phone

			it.email?.let { email ->
				findViewById<LinearLayout>(R.id.contact_mail_layout).visibility = View.VISIBLE
				findViewById<TextView>(R.id.contactMail).text = email
			}

			it.birthday?.let { birthday ->
				findViewById<LinearLayout>(R.id.contact_birthday_layout).visibility = View.VISIBLE
				findViewById<TextView>(R.id.contactBirthday).text = birthday
			}

			it.isFemale?.let { isFemale ->
				findViewById<LinearLayout>(R.id.contact_Sex_layout).visibility = View.VISIBLE
				findViewById<TextView>(R.id.contactSex).text = when (isFemale) {
					true -> "여성"
					false -> "남성"
				}
			}

			it.memo?.let { memo ->
				findViewById<LinearLayout>(R.id.contact_memo_layout).visibility = View.VISIBLE
				findViewById<TextView>(R.id.contactMemo).text = memo
			}
		}
	}
}