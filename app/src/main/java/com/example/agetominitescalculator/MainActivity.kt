package com.example.agetominitescalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var datePickerButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datePickerButton=findViewById(R.id.btnDatePicker)

        datePickerButton.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            { view, year, month, day ->
                Toast.makeText(this,"DatePickerWorks",Toast.LENGTH_SHORT).show()
            }
            ,year
            ,month
            ,day).show()
    }
}