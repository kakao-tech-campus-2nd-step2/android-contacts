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
import androidx.core.view.isVisible

class ContactInfoActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_contact_info)

		val contactDate =
			if (Build.VERSION.SDK_INT >= 33) {
				intent.getSerializableExtra(ContactData.CONTACT_DATA_KEY, ContactData::class.java)
			} else {
				intent.getSerializableExtra(ContactData.CONTACT_DATA_KEY) as ContactData?
			}

		contactDate?.let {
			findViewById<TextView>(R.id.contactName).text = it.name
			findViewById<TextView>(R.id.contactPhone).text = it.phone

			findViewById<LinearLayout>(R.id.contact_mail_layout).isVisible = it.email != null
			findViewById<TextView>(R.id.contactMail).text = it.email

			findViewById<LinearLayout>(R.id.contact_birthday_layout).isVisible = it.birthday != null
			findViewById<TextView>(R.id.contactBirthday).text = it.birthday

			findViewById<LinearLayout>(R.id.contact_Sex_layout).isVisible = it.isFemale != null
			findViewById<TextView>(R.id.contactSex).text = when (it.isFemale) {
				true -> "여성"
				false -> "남성"
				else -> "알 수 없음"
			}

			findViewById<LinearLayout>(R.id.contact_memo_layout).isVisible = it.memo != null
			findViewById<TextView>(R.id.contactMemo).text = it.memo
		}
	}
}