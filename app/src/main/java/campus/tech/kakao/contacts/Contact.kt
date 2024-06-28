package campus.tech.kakao.contacts

data class Contact(
    val name: String,
    val phone: String,
    val email: String,
    val birthday: String?,
    val genderId: Int?,
    val memo: String?
)