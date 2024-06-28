package campus.tech.kakao.contacts

object contactRepository {
    private var _contactList : MutableList<Contact> = mutableListOf<Contact>()
    val contactList : List<Contact> = _contactList

    fun addContact(contact: Contact){
        _contactList.add(contact)
    }

    fun removeContact(contact: Contact){
        _contactList.remove(contact)
    }

    fun removeContact(position : Int){
        _contactList.removeAt(position)
    }

}