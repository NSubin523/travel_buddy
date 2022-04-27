package com.example.travel_scheduler.view.user_history.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travel_scheduler.R
import com.example.travel_scheduler.popup.IconPopupProvider
import com.example.travel_scheduler.view.upcoming_trips.TripActivity
import com.example.travel_scheduler.view.user_history.model.CompletedTrips
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class CompletedTripAdapter(private val titleList: ArrayList<CompletedTrips>,
                           private val context: Context
): RecyclerView.Adapter<CompletedTripAdapter.ViewHolder>() {

    private lateinit var iconProvider: IconPopupProvider

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_title,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val completedTrips: CompletedTrips = titleList[position]
        holder.tripTitle.text = completedTrips.title
        holder.date.text = completedTrips.date

        holder.menuButton.setOnClickListener {
            val popupMenu = PopupMenu(context, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.view_completed_trip -> {
                        val intent = Intent(context, TripActivity::class.java)
                        intent.putExtra("Title", holder.tripTitle.text.toString())
                        context.startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.complted_trip_menu)
            try{
                iconProvider = IconPopupProvider()
                iconProvider.iconProvider(popupMenu)
            } catch (e: Exception){
                Log.e("Error: ",e.message.toString())
            } finally {
                popupMenu.show()
            }
        }
        holder.completed.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    public class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tripTitle : TextView = itemView.findViewById(R.id.itemTitle)
        val date : TextView = itemView.findViewById(R.id.itemDate)
        val menuButton: FloatingActionButton = itemView.findViewById(R.id.menuOption)
        val completed: FloatingActionButton = itemView.findViewById(R.id.completed_trip)
    }
}