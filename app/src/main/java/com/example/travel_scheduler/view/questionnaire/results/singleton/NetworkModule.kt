package com.example.travel_scheduler.view.questionnaire.results.singleton

import com.example.travel_scheduler.utils.Utils
import com.example.travel_scheduler.view.questionnaire.results.model.network_service.APIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule private constructor() {

    private val retrofit = Retrofit.Builder().baseUrl(Utils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service: APIService = retrofit.create(APIService::class.java)

    companion object {
        private lateinit var instance: NetworkModule

        fun newInstance(): NetworkModule {
            if (!::instance.isInitialized) {
                instance = NetworkModule()
            }
            return instance
        }
    }
}