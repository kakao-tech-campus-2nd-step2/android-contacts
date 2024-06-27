package campus.tech.kakao.contacts

import java.io.Serializable

data class Contact (
    var name: String,
    var phoneNumber: String,
    var mail: String,
    var birth: String,
    var sex: Int,
    var memo: String) : Serializable {
    val FEMALE: Int = R.id.female
    val MALE: Int   = R.id.male
}