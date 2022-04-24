package com.example.travel_scheduler.view.questionnaire.results.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.travel_scheduler.R
import com.example.travel_scheduler.firebase.FirestoreProviders
import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpResults
import kotlinx.android.synthetic.main.single_destination.view.*

class ResultAdapter(private val context: Context, private val destinations: List<YelpResults>,
                    private val intent: Intent) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_destination,parent,false))
    }

    override fun onBindViewHolder(holder: ResultAdapter.ViewHolder, position: Int) {
        val destination = destinations[position]
        holder.bind(destination)
    }

    override fun getItemCount() = destinations.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val dbAuth = FirestoreProviders(context)

        @SuppressLint("SetTextI18n")
        fun bind(destinations: YelpResults){
            itemView.tvName.text = destinations.name
            itemView.tvName.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(destinations.url)
                itemView.context.startActivity(intent)
            }
            itemView.ratingBar.rating = destinations.rating.toFloat()
            itemView.tvNumReviwes.text = "${destinations.review_count} reviews"
            itemView.tvNumReviwes.setOnClickListener{
                val url = "https://api.yelp.com/v3/businesses/"+destinations.id+"/reviews"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                itemView.context.startActivity(intent)
            }
            itemView.tvAddress.text = destinations.location.address1
            itemView.tvCategory.text = destinations.categories[0].title
            itemView.tvPrice.text = destinations.price
            if(!destinations.is_closed) itemView.openClosed.text = "Open"
            else itemView.openClosed.text = "Closed"

            Glide.with(context).load(destinations.image_url).apply(
                RequestOptions().transforms(
                CenterCrop(), RoundedCorners(20)
            )).into(itemView.imageView)
            itemView.imageView.setOnClickListener{
                val u = Uri.parse("http://maps.google.co.in/maps?q=" + destinations.name)
                val intent = Intent(Intent.ACTION_VIEW,u)
                itemView.context.startActivity(intent)
            }

            itemView.callPhone.setOnClickListener{
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:" + destinations.phone)
                itemView.context.startActivity(dialIntent)
            }
            itemView.addToFavorites.setOnClickListener{
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Add").setMessage("Add "+destinations.name+" to itinerary?")
                    .setNegativeButton("No",null)
                    .setPositiveButton("Yes") { dialogInterface: DialogInterface, i : Int ->
                        dbAuth.databaseProviders(destinations,intent.getStringExtra("Name").toString())
                    }
                val alert = builder.create()
                alert.show()
            }
        }
    }
}