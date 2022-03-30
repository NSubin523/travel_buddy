package com.example.travel_scheduler.firebase

import android.content.Context
import android.content.Intent
import com.example.travel_scheduler.MainActivity
import com.google.firebase.auth.FirebaseAuth

class FirebaseSignOut(private val context: Context) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signOut(){
        auth.signOut()
        val logoutIntent = Intent(context,MainActivity::class.java)
        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(logoutIntent)
    }
}