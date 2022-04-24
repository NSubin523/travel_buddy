package com.example.travel_scheduler.view.upcoming_trips.firebase_adapter

import android.annotation.SuppressLint
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
import com.example.travel_scheduler.view.upcoming_trips.model.TitleData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class TitleAdapter(private val titleList: ArrayList<TitleData>,
                    private val context: Context): RecyclerView.Adapter<TitleAdapter.ViewHolder>() {

    private lateinit var iconProvider: IconPopupProvider

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_title,parent,false)
        return ViewHolder(itemView)
    }

    @SuppressLint("DiscouragedPrivateApi")
    override fun onBindViewHolder(holder: TitleAdapter.ViewHolder, position: Int) {
         val titleObject : TitleData = titleList[position]
         holder.title.text = titleObject.title
         holder.date.text = titleObject.date
         holder.menuButton.setOnClickListener{
             val popupMenu = PopupMenu(context,it)
             popupMenu.setOnMenuItemClickListener { item ->
                 when(item.itemId){
                     R.id.nav_itinerary ->{
                         val intent = Intent(context,TripActivity::class.java)
                         intent.putExtra("Title",holder.title.text.toString())
                         context.startActivity(intent)
                         true
                     }
                     R.id.nav_cancel ->{
                         true
                     }
                     R.id.nav_completed ->{
                         true
                     }
                     else -> false
                 }
             }
             popupMenu.inflate(R.menu.menu_option)
             try{
                 iconProvider = IconPopupProvider()
                 iconProvider.iconProvider(popupMenu)
             } catch (e: Exception){
                 Log.e("Error: ",e.message.toString())
             } finally {
                 popupMenu.show()
             }
         }
    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    public class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.itemTitle)
        val date : TextView = itemView.findViewById(R.id.itemDate)
        val menuButton: FloatingActionButton = itemView.findViewById(R.id.menuOption)
    }
}