package com.example.travel_scheduler.view.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.travel_scheduler.R
import com.example.travel_scheduler.firebase.AuthProviders
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var userName: TextView
    lateinit var emailField: TextView
    lateinit var pwField: TextView
    private lateinit var confirmPwField: TextView
    private lateinit var signUpButton : Button

    private val authProviders = AuthProviders()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        userName = findViewById(R.id.registerName)
        emailField = findViewById(R.id.setEmailField)
        pwField = findViewById(R.id.setPasswordField)
        confirmPwField = findViewById(R.id.setConfirmPassword)
        signUpButton = findViewById(R.id.signUpBtn)

        signUpButton.setOnClickListener{
            showLoading()
            authProviders.registerUser(this,baseContext,userName.text.toString(), emailField.text.toString(),
                         pwField.text.toString(),confirmPwField.text.toString())
        }
    }

    private fun showLoading(){
        loadingLayoutRegister.visibility = View.VISIBLE
    }
}