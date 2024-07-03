package campus.tech.kakao.contacts

import android.os.Parcel
import android.os.Parcelable

data class Contact(
    val name: String?,
    val phoneNumber: String?,
    val mail: String?,
    val birth: String?,
    val gender: String?,
    val memo: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(phoneNumber)
        parcel.writeString(mail)
        parcel.writeString(birth)
        parcel.writeString(gender)
        parcel.writeString(memo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }
}
