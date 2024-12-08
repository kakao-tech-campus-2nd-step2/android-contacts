package campus.tech.kakao.contacts

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contact::class], version = 3)
abstract class ContactDatabase: RoomDatabase() {
	abstract fun contactDao(): ContactDao
}