package com.example.travel_scheduler.view.upcoming_trips

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travel_scheduler.R
import com.example.travel_scheduler.view.upcoming_trips.firebase_adapter.TripAdapter
import com.example.travel_scheduler.view.upcoming_trips.model.TripData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class TripActivity : AppCompatActivity() {

    private lateinit var tripRecyclerView: RecyclerView
    private lateinit var tripList: ArrayList<TripData>
    private lateinit var tripAdapter: TripAdapter
    private lateinit var dbProvider: FirebaseFirestore
    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip)
        supportActionBar?.title = intent.getStringExtra("Title").toString()

        tripRecyclerView = findViewById(R.id.upcomingTripRv)
        tripRecyclerView.layoutManager = LinearLayoutManager(this)
        tripRecyclerView.setHasFixedSize(true)
        tripList = arrayListOf()
        tripAdapter = TripAdapter(this,tripList)
        tripRecyclerView.adapter = tripAdapter
        eventChangeListener()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun eventChangeListener(){
        dbProvider = FirebaseFirestore.getInstance()
        dbProvider.collection("userItinerary").document(userId!!).collection(intent.getStringExtra("Title").toString())
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.i("Firestore Error", error.message.toString())
                }

                for (doc: DocumentChange in value?.documentChanges!!) {
                    if (doc.type == DocumentChange.Type.ADDED) {
                        tripList.add((doc.document.toObject(TripData::class.java)))
                    }
                }
                tripAdapter.notifyDataSetChanged()
            }
    }
}