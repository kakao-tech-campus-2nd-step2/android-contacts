package campus.tech.kakao.contacts

data class ContactDTO (
    val name: String,
    val phone: String,
    val mail: String? = null,
    val birth: String? = null,
    val gender: String? = null,
    val memo: String? = null
)