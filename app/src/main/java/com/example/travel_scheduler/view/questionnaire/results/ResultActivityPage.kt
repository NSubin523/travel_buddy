package com.example.travel_scheduler.view.questionnaire.results

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel_scheduler.R
import com.example.travel_scheduler.view.questionnaire.results.adapter.ResultAdapter
import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpResults
import com.example.travel_scheduler.view.questionnaire.results.viewmodel.ResultModel
import kotlinx.android.synthetic.main.activity_result_page.*

class ResultActivityPage : AppCompatActivity() {
    private val destinations = mutableListOf<YelpResults>()
    private val adapter = ResultAdapter(this,destinations)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        supportActionBar?.title = "Results"

        rvDestinations.adapter = adapter
        rvDestinations.layoutManager = LinearLayoutManager(this)

        val viewModel = ResultModel(adapter,destinations)
        viewModel.getDestinations(intent.getStringExtra("Activity1").toString(),
                                  intent.getStringExtra("Location").toString())
    }
}