package campus.tech.kakao.contacts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Contact (
	@PrimaryKey(autoGenerate = true) val id: Int,
	@ColumnInfo(name = "name") val name: String,
	@ColumnInfo(name = "phone") val phone: Int,
	@ColumnInfo(name = "email") val email: String,
	@ColumnInfo(name = "birth") val birth: String,
	@ColumnInfo(name = "sex") val sex: String,
	@ColumnInfo(name = "memo") val memo: String
){
	constructor(name: String, phone: Int, email: String, birth: String, sex: String, memo: String):
			this(0, name, phone, email, birth, sex, memo)

}