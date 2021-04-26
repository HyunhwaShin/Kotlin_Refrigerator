import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.refrigerator_kotlin.R

class Adapter(val ItemClick:(Food) -> Unit, val ItemLongClick:(Food) -> Unit)
    : RecyclerView.Adapter<Adapter.ViewHolder>() {
        private var foods : List<Food> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(foods[position])
    }

    //ViewHolder 를 Adapter 클래스안에 넣기위해 inner class 사용
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val F_name = itemView.findViewById<TextView>(R.id.f_name)
        private val L_date = itemView.findViewById<TextView>(R.id.l_date)

        fun bind(food: Food) {
            F_name.text = food.foodName
            L_date.text = food.limitDate

            itemView.setOnClickListener {
                ItemClick(food)
            }

            itemView.setOnLongClickListener {
                ItemLongClick(food)
                true
            }
        }
    }

    //화면을 갱신할때/ DB 가 갱신될 때마다 호출
    fun setFoods(foods: List<Food>) {
        this.foods = foods
        notifyDataSetChanged()
    }
}