package campus.tech.kakao.contacts

data class Contact(
    var name: String,
    var tel: String,
    var mail: String? = null,
    var bDay: String? = null,
    var gender: Gender? = null,
    var memo: String? = null
)
