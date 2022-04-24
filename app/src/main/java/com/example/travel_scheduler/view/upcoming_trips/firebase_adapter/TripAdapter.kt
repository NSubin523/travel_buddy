package com.example.travel_scheduler.view.upcoming_trips.firebase_adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.travel_scheduler.R
import com.example.travel_scheduler.view.upcoming_trips.model.TripData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TripAdapter(private val context: Context, private val tripList: ArrayList<TripData>)
    : RecyclerView.Adapter<TripAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_destination,parent,false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TripAdapter.ViewHolder, position: Int) {
        val tripObject: TripData = tripList[position]
        holder.tvName.text = tripObject.name
        holder.tvName.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(tripObject.url)
            context.startActivity(intent)
        }
        Glide.with(context).load(tripObject.image_url).apply(
            RequestOptions().transforms(
                CenterCrop(), RoundedCorners(20)
            )).into(holder.image)
        holder.ratingBar.rating = tripObject.rating!!
        holder.numReviews.text = "${tripObject.review_count.toString()} reviews"
        holder.addressBar.text = tripObject.address
        holder.tvPrice.text = tripObject.price
        holder.tvCategory.text = tripObject.category
        holder.openClosed.text = tripObject.status
        holder.addToFavBtn.visibility = View.GONE
        holder.callBtn.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + tripObject.phone)
            context.startActivity(dialIntent)
        }
    }

    override fun getItemCount(): Int {
        return tripList.size
    }
    public class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName : TextView = itemView.findViewById(R.id.tvName)
        val image: ImageView = itemView.findViewById(R.id.imageView)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        val numReviews: TextView = itemView.findViewById(R.id.tvNumReviwes)
        val addressBar: TextView = itemView.findViewById(R.id.tvAddress)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val openClosed: TextView = itemView.findViewById(R.id.openClosed)
        val callBtn: Button = itemView.findViewById(R.id.callPhone)
        val addToFavBtn: FloatingActionButton = itemView.findViewById(R.id.addToFavorites)
    }
}