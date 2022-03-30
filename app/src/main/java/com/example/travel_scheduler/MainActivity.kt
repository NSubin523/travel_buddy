package com.example.travel_scheduler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.travel_scheduler.firebase.FirebaseSignIn
import com.example.travel_scheduler.firebase.FirebaseSignOut
import com.example.travel_scheduler.register.RegisterActivity

class MainActivity : AppCompatActivity() {

    lateinit var loginBtn: Button
    lateinit var registerLink: TextView
    private val emailField: TextView by lazy{
        findViewById(R.id.setEmailField)
    }
    private val passwordField: TextView by lazy{
        findViewById(R.id.setPasswordField)
    }
    private val loginFirebase: FirebaseSignIn by lazy{
        FirebaseSignIn(this)
    }

    private val userStatus : FirebaseSignOut by lazy{
        FirebaseSignOut(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        loginBtn = findViewById(R.id.buttonLogin)
        registerLink = findViewById(R.id.registerText)

        userStatus.checkUserStatus()

        loginBtn.setOnClickListener {
            loginFirebase.signIn(emailField.text.toString(),passwordField.text.toString())
        }
        registerLink.setOnClickListener{
            this.navigateToRegistration()
        }
    }

    private fun navigateToRegistration(){
        val registerIntent = Intent(this,RegisterActivity::class.java)
        startActivity(registerIntent)
    }
}