package com.example.travel_scheduler.view.explore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel_scheduler.databinding.ActivityExploreBinding
import com.example.travel_scheduler.view.questionnaire.results.adapter.ResultAdapter
import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpResults
import com.example.travel_scheduler.view.questionnaire.results.viewmodel.ResultModel

class Explore : AppCompatActivity() {
    private lateinit var binding: ActivityExploreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Explore places"
        bodyViaViewModel()
    }
    private fun bodyViaViewModel(){
        val destination = mutableListOf<YelpResults>()
        val adapter = ResultAdapter(this,destination,intent)
        binding.rvExplore.adapter = adapter
        binding.rvExplore.layoutManager = LinearLayoutManager(this)
        val viewModel = ResultModel(adapter,destination)
        viewModel.getDestinations("Restaurants and Museums","Washington DC")
    }
}