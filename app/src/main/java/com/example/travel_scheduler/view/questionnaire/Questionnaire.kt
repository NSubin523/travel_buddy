package com.example.travel_scheduler.view.questionnaire

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.example.travel_scheduler.R
import com.example.travel_scheduler.view.questionnaire.results.ResultActivityPage
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_questionnaire.*
import java.text.SimpleDateFormat
import java.util.*

class Questionnaire : AppCompatActivity() {

    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner
    private lateinit var spinner4: Spinner
    private var buttonDate: Button? = null
    private var textviewDate: TextView? = null
    private var cal: Calendar = Calendar.getInstance()
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var handler: Handler
    private lateinit var setLocation: TextView
    private lateinit var setTripName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)
        supportActionBar?.hide()

        textviewDate = this.text_view_date_1
        buttonDate = this.button_date_1
        floatingActionButton = findViewById(R.id.floatingActionButton)
        setLocation = findViewById(R.id.setLocation)
        setTripName = findViewById(R.id.setTripName)

        textviewDate!!.text = "--/--/----"
        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)
        spinner3 = findViewById(R.id.spinner3)
        spinner4 = findViewById(R.id.spinner4)
        setSpinner(spinner1)
        setSpinner(spinner2)
        setSpinner(spinner3)
        setSpinner(spinner4)

        dateSetListener(buttonDate,cal)
        floatingActionButton.setOnClickListener{
            if(!TextUtils.isEmpty(setLocation.text.toString())
                && !TextUtils.isEmpty(textviewDate!!.text.toString())
                && !TextUtils.isEmpty(setTripName.text.toString())){
                showLoading()
                navigateToResults(setTripName.text.toString(),textviewDate!!.text.toString(),
                                  setLocation.text.toString(), spinner1,spinner2,spinner3,spinner4)
            }
            else{
                Toast.makeText(baseContext,"Error: Missing Credentials",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setSpinner(spinner: Spinner){
        ArrayAdapter.createFromResource(
            this,
            R.array.interests_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    private fun dateSetListener(buttonDate: Button?, cal: Calendar){
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        buttonDate!!.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textviewDate!!.text = sdf.format(cal.time)
    }

    private fun navigateToResults(tripName: String, date: String,location: String,spinner1: Spinner,
                                  spinner2: Spinner, spinner3: Spinner, spinner4: Spinner){
        handler = Handler()
        handler.postDelayed({
            val travelSchedule = Intent(applicationContext,ResultActivityPage::class.java)
            travelSchedule.putExtra("Location",location)
            travelSchedule.putExtra("Name",tripName)
            travelSchedule.putExtra("Activity1",spinner1.selectedItem.toString())
            travelSchedule.putExtra("Activity2",spinner2.selectedItem.toString())
            travelSchedule.putExtra("Activity3",spinner3.selectedItem.toString())
            travelSchedule.putExtra("Activity4",spinner4.selectedItem.toString())
            travelSchedule.putExtra("Date",date)
            startActivity(travelSchedule)
            this.finish()
        },2000)
    }

    private fun showLoading(){
        load.visibility = View.VISIBLE
    }
}