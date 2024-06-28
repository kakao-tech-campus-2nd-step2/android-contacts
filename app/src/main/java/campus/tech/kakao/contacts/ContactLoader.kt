package campus.tech.kakao.contacts

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import campus.tech.kakao.contacts.model.Contact

class ContactLoader (private val context: Context) {
    fun loadContact():List<Contact>{
        val resolver: ContentResolver = context.contentResolver
        val phoneUri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        val result = mutableListOf<Contact>()

        val cursor:Cursor? = resolver.query(phoneUri, projection, null, null, null)

        while(cursor?.moveToNext() == true){
            val nameIndex = cursor.getColumnIndex(projection[1])
            val numberIndex = cursor.getColumnIndex(projection[2])

            val name = cursor.getString(nameIndex)
            val phoneNumber = cursor.getString(numberIndex).replace("-", "")

            val contact = Contact(name, phoneNumber, null, null, null, null)
            result.add(contact)
        }

        cursor?.close()
        return result
    }
}