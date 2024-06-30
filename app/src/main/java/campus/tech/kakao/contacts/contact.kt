package campus.tech.kakao.contacts

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey

data class Contactdata(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String,
    val gender: String,
    val email: String,
    val message: String,
    val birthday: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(gender)
        parcel.writeString(email)
        parcel.writeString(message)
        parcel.writeString(birthday)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contactdata> {
        override fun createFromParcel(parcel: Parcel): Contactdata {
            return Contactdata(parcel)
        }

        override fun newArray(size: Int): Array<Contactdata?> {
            return arrayOfNulls(size)
        }
    }
}