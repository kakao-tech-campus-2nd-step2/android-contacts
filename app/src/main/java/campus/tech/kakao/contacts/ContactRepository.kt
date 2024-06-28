package campus.tech.kakao.contacts

class ContactRepository {
    private val contacts = mutableListOf<Contact>()

    fun addContact(contact: Contact) {
        contacts.add(contact)
    }

    fun getContacts(): List<Contact> {
        return contacts
    }

    fun getContact(name: String): Contact? {
        return contacts.find { it.name == name }
    }

    fun removeContact(name: String) {
        contacts.removeIf { it.name == name }
    }
}