package campus.tech.kakao.contacts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import campus.tech.kakao.contacts.database.Contact
import campus.tech.kakao.contacts.database.ContactDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val database: ContactDatabase = ContactDatabase.getDatabase(application)
    private val contactDao = database.contactDao()

    val allContacts: LiveData<List<Contact>> = contactDao.getAll()

    fun insert(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            contactDao.insert(contact)
        }
    }

    fun delete(contactId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            contactDao.delete(contactId)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            contactDao.deleteAll()
        }
    }
}