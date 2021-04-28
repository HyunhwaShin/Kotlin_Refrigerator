package com.example.refrigerator_kotlin

import Adapter
import Food
import FoodViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

//MainAc 의 역할
//ViewModel 의 instance 를 만들고 이를 관찰하는 역할

class MainActivity : AppCompatActivity() {

    private lateinit var foodViewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set ItemClick & LongItemClick
        val adapter = Adapter({ food ->
            //
        },{ food ->
        deleteDialog(food)
        })

        val lm = LinearLayoutManager(this)
        main_recyclerview.adapter = adapter
        main_recyclerview.layoutManager = lm
        main_recyclerview.setHasFixedSize(true)

        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)

        foodViewModel.getAll().observe(this, Observer<List<Food>> { foods ->
            //update UI
            adapter.setFoods(foods!!)
        })
    }

    private fun deleteDialog(food: Food){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("삭제")
            .setMessage("삭제하시겠습니까?")
            .setNegativeButton("취소"){ _, _ -> }
            .setPositiveButton("확인"){ _, _ -> foodViewModel.delete(food)
            }
        builder.show()
    }
}