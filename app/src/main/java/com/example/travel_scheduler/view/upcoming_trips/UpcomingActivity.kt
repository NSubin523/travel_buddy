package com.example.travel_scheduler.view.upcoming_trips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travel_scheduler.R

class UpcomingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming)
        supportActionBar?.title = "Upcoming Trips"

        val list = ArrayList<String>()
        list.add(intent.getStringExtra("Location").toString())
    }
}