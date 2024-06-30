package campus.tech.kakao.contacts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val tel: String,
    val mail: String,
    val birth: String,
    val gender: String,
    val memo: String
): Parcelable