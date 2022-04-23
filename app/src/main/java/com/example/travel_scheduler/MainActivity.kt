package com.example.travel_scheduler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.travel_scheduler.firebase.FirebaseSignIn
import com.example.travel_scheduler.firebase.FirebaseSignOut
import com.example.travel_scheduler.firebase.GoogleSignUp
import com.example.travel_scheduler.view.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var loginBtn: Button
    lateinit var googleSignInBtn: Button
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
    private val googleSignIn : GoogleSignUp by lazy{
        GoogleSignUp(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        loginBtn = findViewById(R.id.buttonLogin)
        registerLink = findViewById(R.id.registerText)
        googleSignInBtn = findViewById(R.id.googleSignUp)

        userStatus.checkUserStatus()

        loginBtn.setOnClickListener {
            showLoading()
            loginFirebase.signIn(emailField.text.toString(),passwordField.text.toString())
            this.finish()
        }
        registerLink.setOnClickListener{
            this.navigateToRegistration()
        }
        googleSignInBtn.setOnClickListener{
            showLoading()
            googleSignIn.signIn()
        }
    }

    private fun navigateToRegistration(){
        val registerIntent = Intent(this,RegisterActivity::class.java)
        startActivity(registerIntent)
    }

    private fun showLoading(){
        loadingLayout.visibility = View.VISIBLE
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        googleSignIn.onActivityResult(requestCode, resultCode, data)
    }
}