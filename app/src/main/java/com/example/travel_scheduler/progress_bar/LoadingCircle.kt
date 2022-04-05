package com.example.travel_scheduler.progress_bar

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.travel_scheduler.R

class LoadingCircle:AppCompatActivity() {
    private val progressBar: ViewGroup by lazy {
        findViewById(R.id.loadingLayout)
    }

    fun showLoading(){
        progressBar.visibility = View.VISIBLE
    }
}