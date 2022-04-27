package com.example.travel_scheduler.firebase_test

import android.content.Context
import com.example.travel_scheduler.firebase.FirestoreProviders
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class TestDatabase(private val context: Context){
    @Test
    fun `Test Firestore database`(){
        val result = FirestoreProviders(context).storeItineraryTitleAndDate(
            "Hello Hello","testing the date"
        )
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun `Testing delete document`(){
        val result = FirestoreProviders(context).deleteDocument(
            "Just",
            "A",
            "dummy",
            ""
        )
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun `Test database flag`(){
        val result = FirestoreProviders(context).flagCompletedTrip(
            "Flag",
            "this"
        )
        assertThat(result).isEqualTo(true)
    }
}