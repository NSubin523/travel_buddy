package com.example.travel_scheduler.firebase

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.travel_scheduler.view.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class FirebaseSignIn(private val context: Context) {

    private var auth: FirebaseAuth = Firebase.auth

    fun signIn(email: String, pw: String){
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pw)){
            Toast.makeText(context,"Credentials Empty !!", Toast.LENGTH_SHORT).show()
        }
        else {
            try {
                auth.signInWithEmailAndPassword(
                    email,
                    pw
                )
                    .addOnCompleteListener { t ->
                        if (t.isSuccessful) {
                            Toast.makeText(context, "Welcome !!", Toast.LENGTH_SHORT)
                                .show()
                            val startHomeIntent = Intent(context, HomeActivity::class.java)
                            startHomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startHomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            context.startActivity(startHomeIntent)
                        } else {
                            Log.ERROR
                            Toast.makeText(context, "Invalid Credentials !!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            } catch (e: Exception){
                Log.w("LOGIN_TAG",e.message.toString())
                Toast.makeText(context,"Cannot connect to Firebase 404", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}