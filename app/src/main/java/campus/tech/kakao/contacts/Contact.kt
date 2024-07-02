package campus.tech.kakao.contacts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val phoneNum: String,
    val email: String,
    val birthday: String,
    val gender: Gender? = null,
    val memo: String,
) : Parcelable
