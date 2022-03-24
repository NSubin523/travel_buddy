package com.example.travel_scheduler.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travel_scheduler.MainActivity
import com.example.travel_scheduler.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    lateinit var signOutBtn: Button
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        signOutBtn = findViewById(R.id.signOut)
        auth = Firebase.auth

        signOutBtn.setOnClickListener{
            auth.signOut()
            val signOutIntent = Intent(applicationContext,MainActivity::class.java)
            signOutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            signOutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(signOutIntent)
        }
    }
}