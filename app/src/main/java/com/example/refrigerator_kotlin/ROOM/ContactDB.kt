package com.example.refrigerator_kotlin.ROOM

import Contact
import ContactDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version=1)
abstract class ContactDB : RoomDatabase(){

    abstract fun contactDao(): ContactDao

    //static 의 의미이지만 완전히 동일하지는 않다
    companion object{
        private var INSTANCE : ContactDB? = null

        // getInstance 는 여러 thread 가 접근하지 못하도록 synchronized 로 설정
        fun getInstance(context: Context) : ContactDB?{
            if(INSTANCE == null){
                synchronized(ContactDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ContactDB::class.java, "contact")
                        .fallbackToDestructiveMigration() //갱신될 때 기존의 테이블 버리고 새로 사용
                        .build()
                }
            }
            return INSTANCE
        }
    }
}