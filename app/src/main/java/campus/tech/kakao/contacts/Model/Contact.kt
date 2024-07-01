package campus.tech.kakao.contacts.Model

import java.util.Date

data class Contact(
    var name: String,
    var tel: String,
    var mail: String? = null,
    var bDay: Date? = null,
    var gender: Gender? = null,
    var memo: String? = null
)
