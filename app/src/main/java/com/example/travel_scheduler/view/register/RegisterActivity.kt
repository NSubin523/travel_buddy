package com.example.travel_scheduler.view.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.travel_scheduler.databinding.ActivityRegisterBinding
import com.example.travel_scheduler.firebase.AuthProviders

class RegisterActivity : AppCompatActivity() {

    // 1. Initialize the binding object
    private lateinit var binding: ActivityRegisterBinding

    private val authProviders = AuthProviders()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inflate the layout using the generated binding class
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // 3. Access views via 'binding' (no more findViewById or lateinit vars needed)
        binding.signUpBtn.setOnClickListener {
            showLoading()

            // Accessing text from fields directly through the binding object
            authProviders.registerUser(
                this,
                baseContext,
                binding.registerName.text.toString(),
                binding.setEmailField.text.toString(),
                binding.setPasswordField.text.toString(),
                binding.setConfirmPassword.text.toString()
            )
        }
    }

    private fun showLoading() {
        // Fixes the "Unresolved reference: loadingLayoutRegister" error
        binding.loadingLayoutRegister.visibility = View.VISIBLE
    }
}