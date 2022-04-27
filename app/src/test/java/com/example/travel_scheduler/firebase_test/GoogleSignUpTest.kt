package com.example.travel_scheduler.firebase_test

import android.app.Activity
import com.example.travel_scheduler.firebase.GoogleSignUp
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class GoogleSignUpTest(private val activity: Activity) {
    @Test
    fun `Check Google Sign Up`(){
        val result = GoogleSignUp(activity).firebaseAuthWithGoogle("This isn't an id")
        assertThat(result).isEqualTo(false)
    }
}