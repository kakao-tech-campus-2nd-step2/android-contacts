package campus.tech.kakao.contacts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val phoneNumber: String,
    val emailAddress: String,
    val birthday: String?,
    val gender: String?,
    val note: String?
) : Parcelable
