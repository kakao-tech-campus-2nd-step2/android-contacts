package campus.tech.kakao.contacts.model

import java.io.Serializable
import java.time.LocalDate

data class Contact(var name:String, var phoneNumber:String, var email:String?, var gender:Int?, var birthday: LocalDate?, var memo:String?): Serializable