package campus.tech.kakao.contacts
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    private val repository: ContactRepository = ContactRepository()

    fun addContact(contact: Contact) {
        repository.addContact(contact)
    }

    fun getContacts(): List<Contact> {
        return repository.getContacts()
    }

    fun getContact(name: String): Contact? {
        return repository.getContact(name)
    }
}