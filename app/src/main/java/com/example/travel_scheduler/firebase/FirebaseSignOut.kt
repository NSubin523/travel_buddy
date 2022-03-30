package com.example.travel_scheduler.firebase

import android.content.Context
import android.content.Intent
import com.example.travel_scheduler.MainActivity
import com.example.travel_scheduler.view.HomeActivity
import com.google.firebase.auth.FirebaseAuth

class FirebaseSignOut(private val context: Context) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signOut(){
        val logoutIntent = Intent(context,MainActivity::class.java)
        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        auth.signOut()
        context.applicationContext.startActivity(logoutIntent)
    }

    fun checkUserStatus(){
        val user = auth.currentUser
        if (user!=null){
            val goToHome = Intent(context,HomeActivity::class.java)
            goToHome.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.applicationContext.startActivity(goToHome)
        }
    }
}