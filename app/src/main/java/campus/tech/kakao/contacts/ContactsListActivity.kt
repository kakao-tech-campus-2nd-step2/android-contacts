package campus.tech.kakao.contacts

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactsListActivity : AppCompatActivity() {
	lateinit var contactsList: RecyclerView
	lateinit var emptyInfoView: TextView
	lateinit var addButton: FloatingActionButton

	val contactsDataList = mutableListOf<ContactData>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_contacts_list)

		contactsList = findViewById(R.id.contacts_list_recyclerview)
		emptyInfoView = findViewById(R.id.contact_list_empty_textview)
		addButton = findViewById(R.id.contact_add_button)

		contactsList.adapter = ContactsListAdapter(contactsDataList, LayoutInflater.from(this), this)
		contactsList.layoutManager = LinearLayoutManager(this)

		val startActivityLauncher: ActivityResultLauncher<Intent> =
			registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
				when (result.resultCode) {
					RESULT_OK -> {
						val contactData = when (Build.VERSION.SDK_INT) {
							in 33..Int.MAX_VALUE ->
								result.data?.getSerializableExtra("contactData", ContactData::class.java)
							else ->
								result.data?.getSerializableExtra("contactData") as ContactData?
						}?.let { data ->
							contactsDataList.add(data)
							contactsList.adapter?.notifyItemInserted(contactsDataList.size - 1)
							emptyInfoView.visibility = View.GONE
						}
					}
				}
			}

		addButton.setOnClickListener {
			val intent = Intent(this, ContactRegisterActivity::class.java)
			startActivityLauncher.launch(intent)
		}
	}
}