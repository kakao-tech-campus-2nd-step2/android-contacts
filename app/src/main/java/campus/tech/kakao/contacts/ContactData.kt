package campus.tech.kakao.contacts

import java.io.Serializable

data class ContactData (
	val name: String,
	val phone: String,
	var email: String? = null,
	var birthday: String? = null,
	var isFemale: Boolean? = null,
	var memo: String? = null
): Serializable