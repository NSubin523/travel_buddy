package com.example.travel_scheduler.view.explore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel_scheduler.R
import com.example.travel_scheduler.view.questionnaire.results.adapter.ResultAdapter
import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpResults
import com.example.travel_scheduler.view.questionnaire.results.viewmodel.ResultModel
import kotlinx.android.synthetic.main.activity_explore.*

class Explore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)
        bodyViaViewModel()
    }
    private fun bodyViaViewModel(){
        val destination = mutableListOf<YelpResults>()
        val adapter = ResultAdapter(this,destination,intent)
        rvExplore.adapter = adapter
        rvExplore.layoutManager = LinearLayoutManager(this)
        val viewModel = ResultModel(adapter,destination)
        viewModel.getDestinations("Restaurants and Museums","Washington DC")
    }
}