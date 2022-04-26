package com.example.travel_scheduler.view.upcoming_trips.firebase_adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travel_scheduler.R
import com.example.travel_scheduler.firebase.FirestoreProviders
import com.example.travel_scheduler.popup.IconPopupProvider
import com.example.travel_scheduler.utils.Utils
import com.example.travel_scheduler.view.upcoming_trips.TripActivity
import com.example.travel_scheduler.view.upcoming_trips.model.TitleData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class TitleAdapter(private val titleList: ArrayList<TitleData>,
                    private val context: Context): RecyclerView.Adapter<TitleAdapter.ViewHolder>() {

    private lateinit var iconProvider: IconPopupProvider
    private lateinit var dbProviders: FirestoreProviders
    private lateinit var handler: Handler

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_title,parent,false)
        return ViewHolder(itemView)
    }

    @SuppressLint("DiscouragedPrivateApi", "NotifyDataSetChanged")
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
                         dbProviders = FirestoreProviders(context)
                         val builder = AlertDialog.Builder(context)
                         builder.setTitle("Cancel").setMessage("Cancel this itinerary?")
                             .setNegativeButton("No",null)
                             .setPositiveButton("Yes") { dialogInterface: DialogInterface, i : Int ->
                                 dbProviders.deleteDocument(titleObject.title.toString(),
                                     Utils.itineraryTitle,Utils.titleList,Utils.deleted)
                                 notifyDataSetChanged()
                             }
                         val alert = builder.create()
                         alert.show()
                         true
                     }
                     R.id.nav_completed ->{
                         dbProviders = FirestoreProviders(context)
                         handler = Handler()
                         val time : Long = 2000
                         var flag = false
                         holder.completed.visibility = View.VISIBLE
                         if (holder.completed.visibility == View.VISIBLE){
                             flag = true
                         }
                         if (flag){
                             dbProviders.flagCompletedTrip(titleObject.title.toString(),
                                                        titleObject.date.toString())
                         }
                         handler.postDelayed({
                             holder.titleContainer.visibility = View.GONE
                             dbProviders.deleteDocument(titleObject.title.toString(),
                                 Utils.itineraryTitle,Utils.titleList,Utils.completed)
                             notifyDataSetChanged()
                         },time)
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
        val completed: FloatingActionButton = itemView.findViewById(R.id.completed_trip)
        val titleContainer: ViewGroup = itemView.findViewById(R.id.title_container)
    }
}