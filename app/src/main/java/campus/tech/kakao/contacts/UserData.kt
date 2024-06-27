package campus.tech.kakao.contacts

import java.io.Serializable
data class UserData(
    val name: String,
    val phoneNumber: Int
) : Serializable