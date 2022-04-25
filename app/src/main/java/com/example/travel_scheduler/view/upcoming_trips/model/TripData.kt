package com.example.travel_scheduler.view.upcoming_trips.model

data class TripData(
    var name: String? = null,
    var address: String? = null,
    var category: String? = null,
    var image_url: String? = null,
    var phone: String? = null,
    var price: String? = null,
    var rating: Float? = null,
    var review_count: Int? = null,
    var status: String? = null,
    var url: String? = null,
    var documentId: String? = null
)
