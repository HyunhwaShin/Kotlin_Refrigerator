package com.example.refrigerator_kotlin.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.refrigerator_kotlin.R

class Splash : Activity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
        }, 2000L)

    }

}