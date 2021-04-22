package com.example.refrigerator_kotlin.Repository

import Food
import FoodDB
import FoodDao
import android.app.Application
import androidx.lifecycle.LiveData
import java.lang.Exception

//Repository 의 역할
//food, Dao, DB 초기화 and ViewModel 에서 DB 접근 요청할 때 수행할 함수

class FoodRepository(application: Application) {

    private val foodDB = FoodDB.getInstance(application)!!
    private val foodDao : FoodDao = foodDB.foodDao()
    private val foods : LiveData<List<Food>> = foodDao.getAll()

    fun getAll() : LiveData<List<Food>> {
        return foods
    }

    //메인 thread 에 접근하려하면 오류 발생
    //so 별도의 thread 에서 Room 의 데이터에 접근해야 한다
    fun insert(food : Food){
        try{
            val thread = Thread(Runnable {
                foodDao.insert(food) })
            thread.start()
        }catch (e: Exception){ }
    }

    fun delete(food: Food){
        try{
            val thread = Thread(Runnable {
                foodDao.delete(food) })
            thread.start()
        }catch (e : Exception){ }
    }
}