package campus.tech.kakao.contacts

import android.content.ContentValues
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import campus.tech.kakao.contacts.R.id.female

class MainActivity : AppCompatActivity() {
    private val etName: EditText by lazy {findViewById(R.id.name)}
    private val etPhone: EditText by lazy {findViewById(R.id.phone)}
    private val etmail: EditText by lazy {findViewById(R.id.email)}
    private val etmessage: EditText by lazy {findViewById(R.id.message)}
    private val btnsave: Button by lazy {findViewById(R.id.save)}
    private val btndeny: Button by lazy {findViewById(R.id.deny)}
    private val rgfemale: RadioButton by lazy { findViewById(R.id.female) }
    private val rgmale: RadioButton by lazy { findViewById(R.id.male)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnsave.setOnClickListener{
            saveContact()
            finish()
        }
        btndeny.setOnClickListener {
            finish()
        }
    }
    private fun saveContact() {
        val name = etName.text.toString()
        val phone = etPhone.text.toString()
        val email = etmail.text.toString()
        val message = etmessage.text.toString()
        val female = rgfemale.buttonTintMode.hashCode()
        val male = rgmale.buttonTintMode.hashCode()
        val gender = when {
            female -> "1"
            male -> "0"
            else -> ""
        }
        if(name.isEmpty()&&phone.isNotEmpty()&&email.isNotEmpty()&&message.isNotEmpty()){
            val values = ContentValues().apply {
                put(ContactsContract.Contacts.DISPLAY_NAME,name)
                put(ContactsContract.CommonDataKinds.Phone.NUMBER,phone)
                put(ContactsContract.Contacts.IN_DEFAULT_DIRECTORY,email)
                put(ContactsContract.Contacts.IN_DEFAULT_DIRECTORY,message)
                contentResolver.insert(ContactsContract.Contacts.CONTENT_URI,values)
            }
        } else {
            Toast.makeText(this,"정확한 값을 입력해주세요",Toast.LENGTH_SHORT).show()
        }
    }

}
