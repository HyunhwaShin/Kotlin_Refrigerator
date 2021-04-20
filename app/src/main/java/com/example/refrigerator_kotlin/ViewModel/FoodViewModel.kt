import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class FoodViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FoodRepository(application)
    private val foods = repository.getAll()

    fun getAll() : LiveData<List<Food>> {
        return this.foods
    }

    fun insert(food : Food){
        repository.insert(food)
    }

    fun delete(food : Food){
        repository.delete(food)
    }
}