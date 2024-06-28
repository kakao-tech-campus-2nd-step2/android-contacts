package campus.tech.kakao.contacts

var ContactList: MutableList<contact> = mutableListOf()

data class contact(
    val name: String,
    val oneName: String,
    val phone_number: String
)
