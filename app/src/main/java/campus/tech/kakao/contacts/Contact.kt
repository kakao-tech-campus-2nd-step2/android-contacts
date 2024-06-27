package campus.tech.kakao.contacts

class Contact(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val birthDay: String = "",
    val gender: String = "",
    val memo: String = ""
) {}