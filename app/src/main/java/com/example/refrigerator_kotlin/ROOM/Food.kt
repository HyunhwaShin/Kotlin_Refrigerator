import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="food")
data class Food(
    @PrimaryKey(autoGenerate = true) var id : Long?,
    @ColumnInfo(name ="foodName") var foodName : String,
    @ColumnInfo(name = "limitDate") var limitDate : String,
    @ColumnInfo(name = "upDown") var upDown : String,
    @ColumnInfo(name = "memo") var memo : String


) {
    constructor() : this(null,"","","","")
}