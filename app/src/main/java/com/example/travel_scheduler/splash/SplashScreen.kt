package com.example.travel_scheduler.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.travel_scheduler.MainActivity
import com.example.travel_scheduler.common.composables.SplashContent

@SuppressLint("CustomSplashScreen")
class SplashScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashContent(
                onTimeout = {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            )
        }
    }
}