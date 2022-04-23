package com.example.travel_scheduler.view.home.image_container.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.travel_scheduler.R
import com.example.travel_scheduler.view.home.image_container.model.SliderItem
import com.makeramen.roundedimageview.RoundedImageView

class SliderAdapter internal constructor(
    sliderItems: MutableList<SliderItem>,
    viewPager: ViewPager2
): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private val sliderItems: List<SliderItem>
    private val viewPager: ViewPager2

    init {
        this.sliderItems = sliderItems
        this.viewPager = viewPager
    }


    class SliderViewHolder(item: View): RecyclerView.ViewHolder(item){
        private val imageView: RoundedImageView = item.findViewById(R.id.image_slider)

        fun image(sliderItem: SliderItem){
            imageView.setImageResource(sliderItem.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_image_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.image(sliderItems[position])
        if (position == sliderItems.size - 2){
            viewPager.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    private val runnable  = Runnable{
        sliderItems.addAll(sliderItems)
        notifyDataSetChanged()
    }
}