package com.example.travel_scheduler.view.questionnaire.results.adapter

import android.annotation.SuppressLint
import android.content.Context
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
import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpResults
import kotlinx.android.synthetic.main.activity_questionnaire.view.*
import kotlinx.android.synthetic.main.single_destination.view.*

class ResultAdapter(private val context: Context, private val destinations: List<YelpResults>) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_destination,parent,false))
    }

    override fun onBindViewHolder(holder: ResultAdapter.ViewHolder, position: Int) {
        val destination = destinations[position]
        holder.bind(destination)
    }

    override fun getItemCount() = destinations.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bind(destinations: YelpResults){
            itemView.tvName.text = destinations.name
            itemView.tvName.setOnClickListener{
                val re= Regex("[^A-Za-z]")
                var rName = destinations.name
                rName = re.replace(rName,"").lowercase()
                val url = "https://$rName.com"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
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

            itemView.callPhone.setOnClickListener{
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:" + destinations.phone)
                itemView.context.startActivity(dialIntent)
            }
            itemView.addToFavorites.setOnClickListener{
                //TODO DATABASE STUFFS
            }
        }
    }
}