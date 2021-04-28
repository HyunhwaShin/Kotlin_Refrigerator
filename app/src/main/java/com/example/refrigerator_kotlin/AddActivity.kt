package com.example.refrigerator_kotlin

import Food
import FoodViewModel
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.DatePicker
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity(){

    private lateinit var foodViewModel: FoodViewModel
    private var id : Long? = null
    var state : String? =null

    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)

        //intent not null & get Extras check
        if (intent != null && intent.hasExtra(EXTRA_FOOD_NAME) && intent.hasExtra((EXTRA_FOOD_LIMITDATE))
            && intent.hasExtra((EXTRA_FOOD_ID))){
            add_foodName.setText(intent.getStringExtra(EXTRA_FOOD_NAME))
            add_limitDate.text = intent.getStringExtra(EXTRA_FOOD_LIMITDATE)
            id = intent.getLongExtra(EXTRA_FOOD_ID, -1)
        }
        //저장 button click
        add_save_Button.setOnClickListener {
            val foodName = add_foodName.text.toString().trim()
            val limitDate = add_limitDate.text.toString()
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

           // DatePickerDialog.OnDateSetListener 로 limitDate 설정하기

            //필수사항인 (foodName & limitDate) check 후 저장
            if(foodName.isEmpty() || limitDate.isEmpty()){
                Toast.makeText(this, "음식명 과 유통기한을 입력해주세요.", Toast.LENGTH_LONG).show()
            }else{
                val food = Food(id, foodName, limitDate, upDown, memo)
                foodViewModel.insert(food)
                finish()
            }

        }
    }
    companion object{
        const val EXTRA_FOOD_NAME = "EXTRA_FOOD_NAME"
        const val EXTRA_FOOD_LIMITDATE = " EXTRA_FOOD_LIMITDATE"
        const val EXTRA_FOOD_ID = "EXTRA_FOOD_ID"
    }
}