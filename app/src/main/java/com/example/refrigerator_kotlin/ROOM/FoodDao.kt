import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodDao {

    //all data list return
    @Query("SELECT * FROM food ORDER BY foodName ASC")
    fun getAll() : LiveData<List<Food>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(food: Food)

    @Delete
    fun delete(food: Food)
}