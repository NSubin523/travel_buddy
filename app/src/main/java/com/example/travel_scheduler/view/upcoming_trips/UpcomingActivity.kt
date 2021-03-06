package com.example.travel_scheduler.view.upcoming_trips

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travel_scheduler.R
import com.example.travel_scheduler.view.upcoming_trips.firebase_adapter.TitleAdapter
import com.example.travel_scheduler.view.upcoming_trips.model.TitleData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

class UpcomingActivity : AppCompatActivity() {

    private lateinit var titleRecyclerView: RecyclerView
    private lateinit var titleList: ArrayList<TitleData>
    private lateinit var titleAdapter: TitleAdapter
    private lateinit var dbProvider: FirebaseFirestore
    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming)
        supportActionBar?.title = "Upcoming Trips"

        titleRecyclerView = findViewById(R.id.rvTripTitle)
        titleRecyclerView.layoutManager = LinearLayoutManager(this)
        titleRecyclerView.setHasFixedSize(true)
        titleList = arrayListOf()
        titleAdapter = TitleAdapter(titleList,this)
        titleRecyclerView.adapter = titleAdapter
        eventChangeListener()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun eventChangeListener(){
        dbProvider = FirebaseFirestore.getInstance()
        dbProvider.collection("itineraryTitle").document(userId!!).collection("titleList")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.i("Firestore Error", error.message.toString())
                }

                for (doc: DocumentChange in value?.documentChanges!!) {
                    if (doc.type == DocumentChange.Type.ADDED) {
                        titleList.add((doc.document.toObject(TitleData::class.java)))
                    }
                }
                titleAdapter.notifyDataSetChanged()
            }
    }
}