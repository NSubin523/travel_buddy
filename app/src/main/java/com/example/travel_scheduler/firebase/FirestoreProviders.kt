package com.example.travel_scheduler.firebase

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpResults
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.lang.Exception

class FirestoreProviders(private val context: Context) {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val userId = auth.currentUser?.uid

    fun databaseProviders(result: YelpResults, tripName: String){
        val documentRef = db.collection("userItinerary").document(userId!!)
            .collection(tripName)
        val store: MutableMap<String,Any> = HashMap()
        store["name"] = result.name
        store["image_url"]= result.image_url
        store["rating"] = result.rating
        store["review_count"] = result.review_count
        store["category"] = result.categories[0].title
        store["price"] = result.price
        store["phone"] = result.phone
        store["address"] = result.location.address1
        store["url"] = result.url
        if(!result.is_closed)
            store["status"] = "Open"
        else
            store["status"] = "Closed"
        documentRef.add(store).addOnSuccessListener {
            Toast.makeText(context,"Added to Itinerary",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(context,"Cannot connect to database 404 !!",Toast.LENGTH_SHORT).show()
        }
    }

    fun storeItineraryTitleAndDate(title: String, date: String){
        val documentRef = db.collection("itineraryTitle").document(userId!!)
            .collection("titleList")
        val store: MutableMap<String,Any> = HashMap()
        store["title"] = title
        store["date"] = date
        documentRef.add(store).addOnSuccessListener {
            Toast.makeText(context,"Creating itinerary for given date",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(context,"Could not create Itinerary",Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteDocument(collectionName: String){
        db.collection("itineraryTitle").document(userId!!).collection("titleList")
            .whereEqualTo("title",collectionName)
            .get()
            .addOnSuccessListener {
                OnSuccessListener<QuerySnapshot> { doc ->
                    val batch = db.batch()
                    val snapshotList = doc?.documents
                    for(snap in snapshotList!!){
                        batch.delete(snap.reference)
                    }
                    batch.commit().addOnSuccessListener {
                        Toast.makeText(context,"Deleted from Itinerary", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener{
                        Toast.makeText(context,"Firestore Error", Toast.LENGTH_SHORT).show()
                        Log.e("Firestore Error",it.message.toString())
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("FirestoreException", e.message.toString())
                Toast.makeText(context,"Firestore Error",Toast.LENGTH_SHORT).show()
            }
    }
}