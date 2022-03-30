package com.example.travel_scheduler.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travel_scheduler.R
import com.example.travel_scheduler.firebase.FirebaseSignOut
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    lateinit var signOutBtn: Button
    lateinit var auth: FirebaseAuth

    private val logOutFirebase: FirebaseSignOut by lazy {
        FirebaseSignOut(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        signOutBtn = findViewById(R.id.signOut)
        auth = Firebase.auth

        signOutBtn.setOnClickListener{
            logOutFirebase.signOut()
        }
    }
}