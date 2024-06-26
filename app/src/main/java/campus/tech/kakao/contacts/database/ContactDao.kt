package campus.tech.kakao.contacts.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Query("DELETE FROM contacts WHERE id = :contactId")
    fun delete(contactId: Int)

    @Query("SELECT * FROM contacts")
    fun getAll(): LiveData<List<Contact>>

    @Query("DELETE FROM contacts")
    suspend fun deleteAll()

}