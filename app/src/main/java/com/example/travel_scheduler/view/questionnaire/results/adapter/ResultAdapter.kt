package com.example.travel_scheduler.view.questionnaire.results.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.travel_scheduler.databinding.SingleDestinationBinding
import com.example.travel_scheduler.firebase.FirestoreProviders
import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpResults

class ResultAdapter(
    private val context: Context,
    private val destinations: List<YelpResults>,
    private val intent: Intent
) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 1. Inflate the binding for the specific item layout
        val binding = SingleDestinationBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(destinations[position])
    }

    override fun getItemCount() = destinations.size

    // 2. Update ViewHolder to accept the Binding class
    inner class ViewHolder(private val binding: SingleDestinationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val dbAuth = FirestoreProviders(context)

        @SuppressLint("SetTextI18n")
        fun bind(destination: YelpResults) {
            // 3. Access views via 'binding' instead of 'itemView'
            binding.tvName.text = destination.name
            binding.tvName.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(destination.url))
                context.startActivity(browserIntent)
            }

            binding.ratingBar.rating = destination.rating.toFloat()
            binding.tvNumReviwes.text = "${destination.review_count} reviews"

            binding.tvNumReviwes.setOnClickListener {
                val url = "https://api.yelp.com/v3/businesses/${destination.id}/reviews"
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }

            binding.tvAddress.text = destination.location.address1
            binding.tvCategory.text = destination.categories.getOrNull(0)?.title ?: "N/A"
            binding.tvPrice.text = destination.price

            binding.openClosed.text = if (!destination.is_closed) "Open" else "Closed"

            Glide.with(context)
                .load(destination.image_url)
                .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(20)))
                .into(binding.imageView)

            binding.imageView.setOnClickListener {
                val u = Uri.parse("http://maps.google.co.in/maps?q=${destination.name}")
                context.startActivity(Intent(Intent.ACTION_VIEW, u))
            }

            binding.callPhone.setOnClickListener {
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${destination.phone}"))
                context.startActivity(dialIntent)
            }

            binding.addToFavorites.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle("Add")
                    .setMessage("Add ${destination.name} to itinerary?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                        dbAuth.databaseProviders(destination, intent.getStringExtra("Name").toString())
                    }
                    .show()
            }
        }
    }
}