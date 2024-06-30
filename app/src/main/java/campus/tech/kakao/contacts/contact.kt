package campus.tech.kakao.contacts

import androidx.room.PrimaryKey

data class Contactdata(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String,
    val gender: String,
    val email: String,
    val message: String,
    val birthday: String
)