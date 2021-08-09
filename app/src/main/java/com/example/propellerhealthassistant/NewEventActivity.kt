package com.example.propellerhealthassistant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.propellerhealthassistant.databinding.ActivityNewEventBinding
import com.example.propellerhealthassistent.model.Event
import com.example.propellerhealthassistent.model.Medication
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class NewEventActivity : AppCompatActivity(), Serializable {
    private lateinit var binding: ActivityNewEventBinding
    private lateinit var spinner: Spinner
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private val healthViewModel: HealthViewModel by viewModels()
    private var map =  mutableMapOf<String,String>()
    override fun onCreate(savedInstanceState: Bundle?) { //ACTIVITY TO ADD EVENT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_event)

        binding = ActivityNewEventBinding.inflate(layoutInflater)
        datePicker = findViewById(R.id.datePicker)
        timePicker = findViewById(R.id.timePicker)
        spinner = findViewById(R.id.spinner)
        var medication = healthViewModel.getMedication()
        for(i in medication.indices){
            map.put(medication[i].name.uppercase(), medication[i].medicationtype)
        }
        ArrayAdapter( //INITIALIZE SPINNER
            this,
            android.R.layout.simple_spinner_item,
            map.keys.toMutableList()
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }


        val today = Calendar.getInstance()
        binding.datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), //CALENDAR PICKER INITIALIZED TO TODAYS DATE
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1
            val msg = "You Selected: $day/$month/$year"
            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
        }
    }

    fun saveEvent(view: View) { //SAVE VALUES PICKED IN NEW ACTIVITY
        val eventIntent = Intent()
        val type = spinner.selectedItem.toString()
        val year = datePicker.year
        val month = datePicker.month + 1
        val day = datePicker.dayOfMonth
        val localDate = LocalDate.of(year, month,day)
        val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'")
        val date =  localDate.atStartOfDay().atOffset(ZoneOffset.UTC).format(dtf) //GET DATE FROM DATE PICKER
        var hour = timePicker.hour.toString() //GET TIME FROM TIME PICKER
        if(hour.length == 1){
            hour = "0$hour"
        }
        var min = timePicker.minute.toString()
        if(min.toString().length == 1){
            min = "0$min"
        }
        val time = "$hour:$min"

        var dateTime = date + time +":00.000Z" //MAKES 8601 FORMAT
        val event = Event( dateTime, 0, type!!, map.get(type)!!, healthViewModel.getUser().uid )
        eventIntent.putExtra("EVENT", event)
        setResult(Activity.RESULT_OK, eventIntent)
        Toast.makeText(this, "Saved Event",Toast.LENGTH_SHORT).show()
        finish()
    }
}
