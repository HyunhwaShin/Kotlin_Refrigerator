package com.example.refrigerator_kotlin

import Food
import FoodViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


//MainAc 의 역할
//ViewModel 의 instance 를 만들고 이를 관찰하는 역할

class MainActivity : AppCompatActivity() {

    private lateinit var foodViewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foodViewModel = ViewModelProviders.of(this).get(FoodViewModel::class.java)
        foodViewModel.getAll().observe(this, Observer<List<Food>> { foods ->
            //update UI
        })
    }
}