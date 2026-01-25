package com.example.travel_scheduler.view.questionnaire.results

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel_scheduler.databinding.ActivityResultPageBinding
import com.example.travel_scheduler.view.questionnaire.results.adapter.ResultAdapter
import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpResults
import com.example.travel_scheduler.view.questionnaire.results.viewmodel.ResultModel

class ResultActivityPage : AppCompatActivity() {

    private lateinit var binding: ActivityResultPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = intent.getStringExtra("Name").toString()+" Itinerary"+" for "+intent.getStringExtra("Date").toString()

        setButtonTitle()

        displayBodyOnCall(intent.getStringExtra("Activity1").toString(),intent.getStringExtra("Location").toString())
        binding.button1.setOnClickListener{
            displayBodyOnCall(binding.button1.text.toString(),intent.getStringExtra("Location").toString())
        }

        binding.button2.setOnClickListener{
            displayBodyOnCall(binding.button2.text.toString(),intent.getStringExtra("Location").toString())
        }

        binding.button3.setOnClickListener{
            displayBodyOnCall(binding.button3.text.toString(),intent.getStringExtra("Location").toString())
        }

        binding.button4.setOnClickListener{
            displayBodyOnCall(binding.button4.text.toString(),intent.getStringExtra("Location").toString())
        }
    }

    private fun setButtonTitle(){
        binding.button1.text = intent.getStringExtra("Activity1").toString()
        binding.button2.text = intent.getStringExtra("Activity2").toString()
        binding.button3.text = intent.getStringExtra("Activity3").toString()
        binding.button4.text = intent.getStringExtra("Activity4").toString()
    }

    private fun displayBodyOnCall(content: String, location: String){
        val destination = mutableListOf<YelpResults>()
        val adapter = ResultAdapter(this,destination,intent)
        binding.rvDestinations.adapter = adapter
        binding.rvDestinations.layoutManager = LinearLayoutManager(this)
        val viewModel = ResultModel(adapter,destination)
        viewModel.getDestinations(content,location)
    }
}