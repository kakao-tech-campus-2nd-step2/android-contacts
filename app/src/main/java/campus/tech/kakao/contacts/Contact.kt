package campus.tech.kakao.contacts

class Contact (
    var name: String,
    var phoneNumber: String,
    var mail: String,
    var birth: String,
    var sex: Int,
    var memo: String) {
    val FEMALE: Int = R.id.female
    val MALE: Int   = R.id.male
}