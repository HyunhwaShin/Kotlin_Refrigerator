import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodDao {

    //all data list return
    @Query("SELECT * FROM food")
    fun getAll() : LiveData<List<Food>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(food: Food)

    @Delete
    fun delete(food: Food)
}