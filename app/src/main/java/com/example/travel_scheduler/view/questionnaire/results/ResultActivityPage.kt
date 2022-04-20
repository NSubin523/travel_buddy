package com.example.travel_scheduler.view.questionnaire.results

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travel_scheduler.R
import com.example.travel_scheduler.view.questionnaire.results.adapter.ResultAdapter
import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpResults
import com.example.travel_scheduler.view.questionnaire.results.viewmodel.ResultModel
import kotlinx.android.synthetic.main.activity_result_page.*

class ResultActivityPage : AppCompatActivity() {
    private val destinations = mutableListOf<YelpResults>()
    private val adapter = ResultAdapter(this,destinations)
    private val button1 : Button by lazy{
        findViewById(R.id.button1)
    }
    private val button2 : Button by lazy {
        findViewById(R.id.button2)
    }
    private val button3 : Button by lazy{
        findViewById(R.id.button3)
    }
    private val button4: Button by lazy{
        findViewById(R.id.button4)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        supportActionBar?.title = intent.getStringExtra("Name").toString()+" Itinerary"+" for "+intent.getStringExtra("Date").toString()

        setButtonTitle()

        rvDestinations.adapter = adapter
        rvDestinations.layoutManager = LinearLayoutManager(this)
        val viewModel = ResultModel(adapter,destinations)
        viewModel.getDestinations(intent.getStringExtra("Activity1").toString(),
                                  intent.getStringExtra("Location").toString())
        button1.setOnClickListener{
            viewModel.getDestinations(button1.text.toString(),intent.getStringExtra("Location").toString())
        }

        button2.setOnClickListener{
            viewModel.getDestinations(button2.text.toString(),intent.getStringExtra("Location").toString())
        }
        button3.setOnClickListener{
            viewModel.getDestinations(button3.text.toString(),intent.getStringExtra("Location").toString())
        }
        button4.setOnClickListener{
            viewModel.getDestinations(button4.text.toString(),intent.getStringExtra("Location").toString())
        }
    }

    private fun setButtonTitle(){
        button1.text = intent.getStringExtra("Activity1").toString()
        button2.text = intent.getStringExtra("Activity2").toString()
        button3.text = intent.getStringExtra("Activity3").toString()
        button4.text = intent.getStringExtra("Activity4").toString()
    }

}