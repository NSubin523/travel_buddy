package com.example.travel_scheduler.view.questionnaire.results.model.data

data class YelpSearchResult(
    val total: Int,
    val businesses: List<YelpResults>
)

data class YelpResults(
    val id : String,
    val name : String,
    val rating : Double,
    val price : String,
    val phone: String,
    val url: String,
    val is_closed: Boolean,
    val review_count: Int,
    val distance: Double,
    val image_url : String,
    val categories: List<YelpCategory>,
    val location: YelpLocation
)

data class YelpCategory(
    val title : String
)

data class YelpLocation(
    val address1: String
)