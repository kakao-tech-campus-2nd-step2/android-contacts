package campus.tech.kakao.contacts

data class Contact(
    var name: String,
    var phoneNumber: String,
    var email: String,
    var birthDay: String = "",
    var gender: String = "",
    var memo: String = ""
) {}