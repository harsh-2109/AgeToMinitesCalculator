package com.example.agetominitescalculator

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var datePickerButton: Button
    private lateinit var selectedDateTextView: TextView
    private lateinit var ageInMinitesTextView: TextView
    private lateinit var ageInDays: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datePickerButton = findViewById(R.id.btnDatePicker)
        selectedDateTextView = findViewById(R.id.tvSelectedDate)
        ageInMinitesTextView = findViewById(R.id.tvAgeInMinetes)
        ageInDays = findViewById(R.id.tvAgeInDays)

        datePickerButton.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        with(
            DatePickerDialog(
                this,
                { _, selecetdYear, selectedMonth, selectedDay ->
                    Toast.makeText(
                        this,
                        "Year $selecetdYear, Month ${selectedMonth + 1}, Day $selectedDay",
                        Toast.LENGTH_SHORT
                    ).show()

                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selecetdYear"

                    selectedDateTextView.text = selectedDate

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.CANADA)
                    val theDate = sdf.parse("$selectedDay/${selectedMonth + 1}/$selecetdYear")

                    val selecetedDatesInMinutes = theDate.time / 60000
                    val currentDatesInMinutes =
                        sdf.parse(sdf.format(System.currentTimeMillis())).time / 60000

                    ageInMinitesTextView.text =
                        (currentDatesInMinutes - selecetedDatesInMinutes).toString()

                    val selectedDateInDays = theDate.time / 86400000
                    val currentDateinDays =
                        sdf.parse(sdf.format(System.currentTimeMillis())).time / 86400000

                    ageInDays.text = (currentDateinDays - selectedDateInDays).toString()
                }, year, month, day
            )
        ) {
            datePicker.maxDate = System.currentTimeMillis() - 86400000
            show()
        }
    }
}
