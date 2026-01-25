package com.example.travel_scheduler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.travel_scheduler.databinding.ActivityMainBinding
import com.example.travel_scheduler.firebase.FirebaseSignIn
import com.example.travel_scheduler.firebase.FirebaseSignOut
import com.example.travel_scheduler.firebase.GoogleSignUp
import com.example.travel_scheduler.view.register.RegisterActivity

class MainActivity : AppCompatActivity() {

    // 1. Define the binding variable
    private lateinit var binding: ActivityMainBinding

    // 2. You no longer need these 'lateinit' buttons or 'findViewById' lazies!
    // Everything is accessible via 'binding'

    private val loginFirebase: FirebaseSignIn by lazy {
        FirebaseSignIn(this)
    }

    private val userStatus: FirebaseSignOut by lazy {
        FirebaseSignOut(applicationContext)
    }

    private val googleSignIn: GoogleSignUp by lazy {
        GoogleSignUp(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 3. Initialize binding and set the content view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        userStatus.checkUserStatus()

        // 4. Access views directly through the binding object
        binding.buttonLogin.setOnClickListener {
            showLoading()
            // View Binding automatically converts snake_case IDs to camelCase
            val email = binding.setEmailField.text.toString()
            val password = binding.setPasswordField.text.toString()

            loginFirebase.signIn(email, password)
            this.finish()
        }

        binding.registerText.setOnClickListener {
            this.navigateToRegistration()
        }

        binding.googleSignUp.setOnClickListener {
            showLoading()
            googleSignIn.signIn()
        }
    }

    private fun navigateToRegistration() {
        val registerIntent = Intent(this, RegisterActivity::class.java)
        startActivity(registerIntent)
    }

    private fun showLoading() {
        // This fixes the "Unresolved reference: loadingLayout" error
        binding.loadingLayout.visibility = View.VISIBLE
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        googleSignIn.onActivityResult(requestCode, resultCode, data)
    }
}