package com.example.travel_scheduler.firebase

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travel_scheduler.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class AuthProviders: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    fun registerUser(activity: Activity,context: Context,userName:String,emailField: String,
                     pwField: String, confirmPwField: String){
        auth = Firebase.auth
        if(checkValidations(userName,emailField,pwField,confirmPwField)) {
            try{
                auth.createUserWithEmailAndPassword(emailField, pwField)
                    .addOnCompleteListener(activity) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(ContentValues.TAG, "createUserWithEmail:success")
                            Toast.makeText(context,"Registration Successful !!", Toast.LENGTH_SHORT)
                                .show()
                            val signInIntent = Intent(context, MainActivity::class.java)
                            signInIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            signInIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(signInIntent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                context, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } catch (e: Exception){
                Log.e("FIREBASE_ERROR",e.message.toString())
            }
        }
        else{
            Toast.makeText(context,"One or more credentials empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkValidations(userName:String, emailField:String, pwField:String, confirmPwField: String): Boolean{
        if(userName.isEmpty() && userName.length>10 && emailField.isEmpty() && pwField.isEmpty() && pwField.length<6
            && pwField.length>12 && confirmPwField != pwField)
            return false
        return true
    }
}