package campus.tech.kakao.contacts

object Contacts {

    private lateinit var contacts: ArrayList<Contact>

    init {
        initializeContacts()
    }

    fun initializeContacts() {
        contacts = ArrayList()
    }

    fun getSize(): Int {
        return contacts.size
    }

    fun getContact(idx: Int): Contact {
        return contacts.get(idx)
    }

    fun getList(): ArrayList<Contact> {
        return contacts
    }

    fun findContact(name: String, phoneNumber: String): Int {
        val contact = Contact(name, phoneNumber)
        return contacts.indexOf(contact)
    }

    fun addContact(infos: Array<String>): Boolean {
        val contact = Contact(infos[0], infos[1], infos[2], infos[3], infos[4], infos[5])
        if (findContact(infos[0], infos[1]) == -1) {
            contacts.add(contact)
            return true
        }
        return false
    }

    fun delContact(idx: Int) {
        contacts.removeAt(idx)
    }

}