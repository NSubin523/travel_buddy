package com.example.travel_scheduler.view.questionnaire

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.example.travel_scheduler.R
import com.example.travel_scheduler.databinding.ActivityQuestionnaireBinding
import com.example.travel_scheduler.firebase.FirestoreProviders
import com.example.travel_scheduler.view.questionnaire.results.ResultActivityPage
import java.text.SimpleDateFormat
import java.util.*

class Questionnaire : AppCompatActivity() {

    // 1. Initialize Binding
    private lateinit var binding: ActivityQuestionnaireBinding

    private var cal: Calendar = Calendar.getInstance()
    private val dbProviders = FirestoreProviders(this)

    // Using Looper.getMainLooper() is required in modern Android for Handlers
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Setup View Binding
        binding = ActivityQuestionnaireBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // 3. Replace direct synthetic assignments
        // Old: textviewDate = this.text_view_date_1
        binding.textViewDate1.text = "--/--/----"

        // Setup Spinners
        setSpinner(binding.spinner1)
        setSpinner(binding.spinner2)
        setSpinner(binding.spinner3)
        setSpinner(binding.spinner4)

        dateSetListener(binding.buttonDate1, cal)

        binding.floatingActionButton.setOnClickListener {
            val location = binding.setLocation.text.toString()
            val date = binding.textViewDate1.text.toString()
            val tripName = binding.setTripName.text.toString()

            if (!TextUtils.isEmpty(location) && date != "--/--/----" && !TextUtils.isEmpty(tripName)) {
                showLoading()
                navigateToResults(tripName, date, location)
                dbProviders.storeItineraryTitleAndDate(tripName, date)
            } else {
                Toast.makeText(baseContext, "Error: Missing Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setSpinner(spinner: Spinner) {
        ArrayAdapter.createFromResource(
            this,
            R.array.interests_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun dateSetListener(buttonDate: Button, cal: Calendar) {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

        buttonDate.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.textViewDate1.text = sdf.format(cal.time)
    }

    private fun navigateToResults(tripName: String, date: String, location: String) {
        handler.postDelayed({
            val travelSchedule = Intent(applicationContext, ResultActivityPage::class.java).apply {
                putExtra("Location", location)
                putExtra("Name", tripName)
                putExtra("Activity1", binding.spinner1.selectedItem.toString())
                putExtra("Activity2", binding.spinner2.selectedItem.toString())
                putExtra("Activity3", binding.spinner3.selectedItem.toString())
                putExtra("Activity4", binding.spinner4.selectedItem.toString())
                putExtra("Date", date)
            }
            startActivity(travelSchedule)
            this.finish()
        }, 2000)
    }

    private fun showLoading() {
        // Fixes: Unresolved reference: load
        binding.load.visibility = View.VISIBLE
    }
}