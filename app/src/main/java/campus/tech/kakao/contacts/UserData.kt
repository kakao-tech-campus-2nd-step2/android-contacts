package campus.tech.kakao.contacts

import java.io.Serializable
data class UserData(
    val name: String,
    val phoneNumber: Int,
    val email: String,
    val birthday: String,
    val gender: String,
    val memo: String
) : Serializable