package campus.tech.kakao.contacts

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class ContactDetail : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		//UI 선언
		setContentView(R.layout.activity_contact_detail)
		val name = findViewById<TextView>(R.id.name)
		val phone = findViewById<TextView>(R.id.phone)
		val email = findViewById<TextView>(R.id.mail)
		val emailLayout = findViewById<View>(R.id.mailLayout)
		val birth = findViewById<TextView>(R.id.birth)
		val birthLayout = findViewById<View>(R.id.birthLayout)
		val sex = findViewById<TextView>(R.id.sex)
		val sexLayout = findViewById<View>(R.id.sexLayout)
		val memo = findViewById<TextView>(R.id.memo)
		val memoLayout = findViewById<View>(R.id.memoLayout)

		//데이터베이스 선언
		val database = Room.databaseBuilder(
			applicationContext,
			ContactDatabase::class.java, "database-name"
		).addMigrations(Contact.MIGRATION_1_2, Contact.MIGRATION_2_3).allowMainThreadQueries().build()
		//인텐트를 통해 ID 받기
		val intent = intent
		val id = intent.extras?.getInt("contact-id")
		Log.d("contacttest", "id : "+id)

		//ID에 해당하는 데이터 불러오기
		if (id != null) {
			val contact = database.contactDao().getById(id)
			if (contact != null) {
				Log.d("contacttest", "detail : "+contact.id+", "+contact.name+", "+contact.phone+", "+contact.email+", "+contact.birth+", "+contact.sex+", "+contact.memo)
				if (contact.name != null && contact.name.isNotEmpty()) {
					name.text = contact.name
				}
				if (contact.phone != null && contact.phone.isNotEmpty()) {
					phone.text = contact.phone
				}
				if (contact.email!=null && contact.email.isNotEmpty()) {
					email.text = contact.email
					emailLayout.visibility = View.VISIBLE
				}
				if (contact.birth != null && contact.birth.isNotEmpty()) {
					birth.text = contact.birth
					birthLayout.visibility = View.VISIBLE
				}
				if (contact.sex != null && contact.sex.isNotEmpty()) {
					sex.text = contact.sex
					sexLayout.visibility = View.VISIBLE
				}
				if (contact.memo != null && contact.memo.isNotEmpty()) {
					memo.text = contact.memo
					memoLayout.visibility = View.VISIBLE
				}
			}
			else{
				Log.d("contacttest", "contact is null")
			}
		}
		else{
			Log.d("contacttest", "id("+id+") is null")
		}

	}
}