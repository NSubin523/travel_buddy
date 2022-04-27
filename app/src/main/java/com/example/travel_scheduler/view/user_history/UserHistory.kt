package com.example.travel_scheduler.view.user_history

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travel_scheduler.R
import com.example.travel_scheduler.view.user_history.adapter.CompletedTripAdapter
import com.example.travel_scheduler.view.user_history.model.CompletedTrips
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class UserHistory : AppCompatActivity() {

    private lateinit var completedTripRv: RecyclerView
    private lateinit var titleList: ArrayList<CompletedTrips>
    private lateinit var completedTitleAdapter: CompletedTripAdapter
    private lateinit var dbProvider: FirebaseFirestore
    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_history)

        supportActionBar?.title = "Completed Trips"

        completedTripRv = findViewById(R.id.userHistory)
        completedTripRv.layoutManager = LinearLayoutManager(this)
        completedTripRv.setHasFixedSize(true)
        titleList = arrayListOf()
        completedTitleAdapter = CompletedTripAdapter(titleList,this)
        completedTripRv.adapter = completedTitleAdapter
        eventChangeListener()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun eventChangeListener(){
        dbProvider = FirebaseFirestore.getInstance()
        dbProvider.collection("completedTrips").document(userId!!).collection("completedTrips")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.i("Firestore Error", error.message.toString())
                }

                for (doc: DocumentChange in value?.documentChanges!!) {
                    if (doc.type == DocumentChange.Type.ADDED) {
                        titleList.add((doc.document.toObject(CompletedTrips::class.java)))
                    }
                }
                completedTitleAdapter.notifyDataSetChanged()
            }
    }
}