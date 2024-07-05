package campus.tech.kakao.contacts

import android.os.Parcel
import android.os.Parcelable

//연락처 데이터 객체 클래스
data class Contact (
    val name: String,
    val phone: String,
    val email: String?,
    val birth: String?,
    val gender: String?,
    val memo: String?
) : Parcelable { //intent로 전달하는 거
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    //Contact 객체 데이터 내용을 parcel에 쓰기
    override fun writeToParcel(parcel: Parcel, flags: Int){
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(email)
        parcel.writeString(birth)
        parcel.writeString(gender)
        parcel.writeString(memo)
    }

    //오류 해결
    //Parcelable 필수
    override fun describeContents(): Int {
        return 0
    }

    //Parcelable 객체 생성
    companion object CREATOR: Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        //parcelable 인터페이스의 객체 직렬화 시 필수 - newArray method
        override fun newArray(size:Int):Array<Contact?>{
            return arrayOfNulls(size)
        }
    }

}
