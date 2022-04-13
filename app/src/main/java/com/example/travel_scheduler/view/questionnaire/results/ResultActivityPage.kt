package com.example.travel_scheduler.view.questionnaire.results

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travel_scheduler.R

class ResultActivityPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        supportActionBar?.title = "Results"
    }
}