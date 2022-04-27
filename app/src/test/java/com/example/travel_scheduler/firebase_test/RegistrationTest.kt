package com.example.travel_scheduler.firebase_test

import com.example.travel_scheduler.firebase.AuthProviders
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class RegistrationTest {
    @Test
    fun `Checking Validations of user while registering`(){
        val result = AuthProviders().checkValidations(
            "Subin",
            "random email",
            "123",
            "1234"
        )
        assertThat(result).isFalse()
    }
}