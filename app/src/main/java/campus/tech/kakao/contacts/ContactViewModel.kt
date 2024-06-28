package campus.tech.kakao.contacts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    val contactListLiveData = MutableLiveData<MutableList<contact>>().apply {
        value = ContactList
    }

    fun addContact(contact: contact) {
        ContactList.add(contact)
        contactListLiveData.value = ContactList
        Log.d("ContactViewModel", "Contact added: ${contact.name}, total contacts: ${ContactList.size}")
    }
}
