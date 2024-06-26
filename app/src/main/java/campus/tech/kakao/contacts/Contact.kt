package campus.tech.kakao.contacts

class Contact(
    var name: String, var phoneNumber: String, var mail: String = "",
    var birth: String = "", var gender: String = "", var memo: String = ""
) {

    override fun equals(other: Any?): Boolean {
        if (other is Contact)
            if (this.name == other.name)
                if (this.phoneNumber == other.phoneNumber)
                    return true
        return false
    }

}