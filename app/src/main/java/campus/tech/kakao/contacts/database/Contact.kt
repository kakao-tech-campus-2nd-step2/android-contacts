package campus.tech.kakao.contacts.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String,
    val email: String?,
    val birthday: String?,
    val gender: String?,
    val memo: String?
)