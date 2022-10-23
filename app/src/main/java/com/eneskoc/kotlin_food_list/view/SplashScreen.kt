package com.eneskoc.kotlin_food_list.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.eneskoc.kotlin_food_list.R


@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    companion object {
        const val ANIMATION_TIME: Long = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(this.mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }, ANIMATION_TIME)

    }
}