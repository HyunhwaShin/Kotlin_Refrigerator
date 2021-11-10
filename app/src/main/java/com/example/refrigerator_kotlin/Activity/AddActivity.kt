package com.example.refrigerator_kotlin.Activity

import Food
import FoodViewModel
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.refrigerator_kotlin.R
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*

class AddActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener{

    //private lateinit var foodViewModel: FoodViewModel
    private var id : Long? = null
    var state : String? =null

    var showDate :String? =null
    var deadDate :String? =null

    var Cyear :String? =null
    var Cmonth :String? =null
    var Cday :String? =null


    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        //foodViewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)

        //intent (not null & get Extras) check
        if (intent != null && intent.hasExtra(EXTRA_FOOD_NAME) && intent.hasExtra((EXTRA_FOOD_LIMITDATE))
            && intent.hasExtra((EXTRA_FOOD_ID))) {
            add_foodName.setText(intent.getStringExtra(EXTRA_FOOD_NAME))
            add_limitDate_Button.text = intent.getStringExtra(EXTRA_FOOD_LIMITDATE)
            add_upDown.setText(intent.getStringExtra(EXTRA_FOOD_STATE))
            add_memo.setText(intent.getStringExtra(EXTRA_FOOD_MEMO))
            id = intent.getLongExtra(EXTRA_FOOD_ID, -1)
        }

        //limitDate
        val limitDate = findViewById<Button>(R.id.add_limitDate_Button)
        val cal : Calendar = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH +1)
        val day = cal.get(Calendar.DATE)

        Cyear = year.toString()
        Cmonth = month.toString()
        Cday = day.toString()

        showDate = "유통기한 : " + Cyear + "년" + Cmonth + "월" + Cday + "일"
        deadDate = Cyear + "년" + Cmonth + "월" + Cday + "일"

        limitDate.text = showDate

        //유통기한 버튼
        add_limitDate_Button.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this@AddActivity,this@AddActivity, year, month, day) //현재날짜 보여주기
            datePickerDialog.show()
        }

        //저장 button click
        add_save_Button.setOnClickListener {
            val foodName = add_foodName.text.toString().trim()
            val memo = add_memo.text.toString()

            //switch
            val switch = findViewById<Switch>(R.id.add_upDown)

            switch.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    //off -> on(냉장)
                    state = if(isChecked){
                        "냉장"
                    }else {
                        "냉동"
                    }
                }
            })
            val upDown = state.toString()

            //필수사항인 (foodName & limitDate) check 후 저장
            if(foodName.isEmpty() || limitDate.text.toString().isEmpty()){
                Toast.makeText(this, "음식명 과 유통기한을 입력해주세요.", Toast.LENGTH_LONG).show()
            }else{
                val food = Food(id, foodName, deadDate!!, upDown, memo)
                //foodViewModel.insert(food)
                finish()
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {

        val limitDate = findViewById<Button>(R.id.add_limitDate_Button)

        Cyear = year.toString()
        Cmonth = month.toString()
        Cday = day.toString()

        showDate = "유통기한 : " + Cyear + "년" + Cmonth + "월" + Cday + "일"
        deadDate = Cyear + "년" + Cmonth + "월" + Cday + "일"

        limitDate.text = showDate

    }

    companion object{
        const val EXTRA_FOOD_ID = "EXTRA_FOOD_ID"
        const val EXTRA_FOOD_NAME = "EXTRA_FOOD_NAME"
        const val EXTRA_FOOD_LIMITDATE = " EXTRA_FOOD_LIMITDATE"
        const val EXTRA_FOOD_STATE = "EXTRA_FOOD_STATE"
        const val EXTRA_FOOD_MEMO = "EXTRA_FOOD_MEMO"
    }
}

