package com.eneskoc.kotlin_food_list.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.airbnb.lottie.utils.Utils
import com.eneskoc.kotlin_food_list.R
import com.eneskoc.kotlin_food_list.util.isOnline
import kotlin.system.exitProcess


@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    companion object {
        const val ANIMATION_TIME: Long = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(this.mainLooper).postDelayed({
            if(isOnline(applicationContext)){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.network_alert_title))
                builder.setMessage(getString(R.string.network_alert_desc))

                builder.setPositiveButton(android.R.string.cancel) { _, _ ->
                    finishAffinity();
                    exitProcess(0);
                }
                builder.show()
            }

        }, ANIMATION_TIME)

    }
}