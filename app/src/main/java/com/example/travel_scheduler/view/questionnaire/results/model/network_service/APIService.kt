package com.example.travel_scheduler.view.questionnaire.results.model.network_service

import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface APIService {
    @GET("businesses/search")
    fun getDestinations(
        @Header("Authorization") authHeader: String,
        @Query("term") searchTerm : String,
        @Query("location") location : String,
        @Query("limit") limit: Int,
        @Query("sort_by") sortBy : String,
    ) : Call<YelpSearchResult>
}