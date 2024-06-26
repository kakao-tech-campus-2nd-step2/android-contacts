package campus.tech.kakao.contacts

class Contacts {

    lateinit var contacts: ArrayList<Contact>

    init {
        initializeContacts()
    }

    fun initializeContacts() {
        contacts = ArrayList()
    }

    fun addContact (infos: Array<String>): Boolean {
        val contact = Contact(infos[0], infos[1], infos[2], infos[3], infos[4], infos[5])
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