package com.example.travel_scheduler.view.questionnaire.results.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travel_scheduler.R
import com.example.travel_scheduler.view.questionnaire.results.model.data.YelpResults
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
        fun bind(destinations: YelpResults){
            itemView.destinationName.text = destinations.name
        }
    }
}