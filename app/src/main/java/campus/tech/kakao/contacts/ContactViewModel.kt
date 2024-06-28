package campus.tech.kakao.contacts
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel: ViewModel() {
    private val repository: ContactRepository = ContactRepository.getInstance()
    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> get() = _contacts

    init {
        _contacts.value = repository.getContacts()
    }
    fun addContact(contact: Contact) {
        repository.addContact(contact)
        _contacts.value = repository.getContacts()
    }

    fun getContacts(){
        _contacts.value = repository.getContacts()
    }

    fun getContact(name: String): Contact? {
        return repository.getContact(name)
    }
}