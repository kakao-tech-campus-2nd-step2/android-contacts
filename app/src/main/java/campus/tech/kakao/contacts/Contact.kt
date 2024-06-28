package campus.tech.kakao.contacts

data class Contact(
    var name: String,
    var tel: String,
    var mail: String?,
    var birth: String?,
    var gender: String?,
    var memo: String?
)