package campus.tech.kakao.contacts

class Contacts {

    lateinit var contacts: ArrayList<Contact>

    fun initializeContacts() {
        contacts = ArrayList()
    }

    fun addContact (
        name: String, phoneNumber: String, mail: String,
        birth: String, gender: String, memo: String
    ): Boolean {
        val contact = Contact(name, phoneNumber, mail, birth, gender, memo)
        if (!contacts.contains(contact)) {
            contacts.add(contact)
            return true
        }
        return false
    }

    fun delContact(name: String, phoneNumber: String) {
        val contact = Contact(name, phoneNumber,"","","","")
        contacts.remove(contact)
    }

}