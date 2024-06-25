package campus.tech.kakao.contacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface ContactDao {
	@Insert(onConflict = REPLACE)
	fun insert(contact: Contact)

	@Query("SELECT * FROM Contact")
	fun getAll(): List<Contact>

	@Query("SELECT * FROM Contact WHERE id = :id")
	fun getById(id: Int): Contact

	@Query("DELETE FROM Contact WHERE id = :id")
	fun deleteById(id: Int)

}

