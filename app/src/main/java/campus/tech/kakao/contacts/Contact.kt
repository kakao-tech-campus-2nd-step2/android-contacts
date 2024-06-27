package campus.tech.kakao.contacts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Entity
class Contact (
	@PrimaryKey(autoGenerate = true) val id: Int,
	@ColumnInfo(name = "name") val name: String,
	@ColumnInfo(name = "phone") val phone: Int,
	@ColumnInfo(name = "email") val email: String?,
	@ColumnInfo(name = "birth") val birth: String?,
	@ColumnInfo(name = "sex") val sex: String?,
	@ColumnInfo(name = "memo") val memo: String?
){
	constructor(name: String, phone: Int, email: String?, birth: String?, sex: String?, memo: String?):
			this(0, name, phone, email, birth, sex, memo)

	companion object {
		val MIGRATION_1_2 = object : Migration(1, 2) {
			override fun migrate(database: SupportSQLiteDatabase) {
				database.execSQL(
					"CREATE TABLE new_Contact (" +
							"id INTEGER PRIMARY KEY NOT NULL, " +
							"name TEXT NOT NULL, " +
							"phone INTEGER NOT NULL, " +
							"email TEXT, " +
							"birth TEXT, " +
							"sex TEXT, " +
							"memo TEXT)"
				)
				// 데이터 복사
				database.execSQL(
					"INSERT INTO new_Contact (id, name, phone, email, birth, sex, memo) " +
							"SELECT id, name, CAST(phone AS INTEGER), email, birth, sex, memo FROM Contact")
				// 이전 테이블 제거
				database.execSQL("DROP TABLE Contact")

				// 새 테이블 이름 바꾸기
				database.execSQL("ALTER TABLE new_Contact RENAME TO Contact")
			}
		}
	}
}