package com.example.travel_scheduler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.travel_scheduler.firebase.FirebaseSignIn
import com.example.travel_scheduler.firebase.FirebaseSignOut
import com.example.travel_scheduler.utils.Utils
import com.example.travel_scheduler.view.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var loginBtn: Button
    private lateinit var registerLink: TextView

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
            if(!TextUtils.isEmpty(emailField.text.toString()) || !TextUtils.isEmpty(passwordField.text.toString())){
                showLoading()
                loginFirebase.signIn(emailField.text.toString(),passwordField.text.toString())
                finish()
            }
        }
        registerLink.setOnClickListener{
            this.navigateToRegistration()
        }
    }

    private fun navigateToRegistration(){
        val registerIntent = Intent(this,RegisterActivity::class.java)
        startActivity(registerIntent)
    }

    private fun showLoading(){
        loadingLayout.visibility = View.VISIBLE
    }
}