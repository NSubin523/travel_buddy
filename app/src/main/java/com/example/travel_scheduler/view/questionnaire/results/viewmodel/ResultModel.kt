package com.example.travel_scheduler.view.questionnaire.results.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.travel_scheduler.utils.Utils
import com.example.travel_scheduler.view.questionnaire.results.adapter.ResultAdapter
import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpResults
import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpSearchResult
import com.example.travel_scheduler.view.questionnaire.results.singleton.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultModel(
    private val adapter: ResultAdapter,
    private val results: MutableList<YelpResults>) : ViewModel() {

    fun getDestinations() {
        NetworkModule.newInstance().service.getDestinations("Bearer ${Utils.API_KEY}",
                                                            "Avocado Toast",
                                                            "New York City",
                                                            Utils.limit,
                                                            Utils.default_sorting)
            .enqueue(object : Callback<YelpSearchResult> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<YelpSearchResult>,
                response: Response<YelpSearchResult>
            ) {
                Log.i("RESULT TAG","onResponse: $response")
                val body = response.body()
                if(body == null){
                    Log.w("LOG TAG","Did not receive valid data from Yelp")
                    return
                }
                results.addAll(body.businesses)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i("LOG","onFailure: $t")
            }

        })
    }
}