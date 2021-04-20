import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {

    //all data list return
    @Query("SELECT * FROM contact")
    fun getAll() : LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}