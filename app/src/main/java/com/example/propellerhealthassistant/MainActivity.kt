package com.example.propellerhealthassistant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propellerhealthassistant.adapters.EventListAdapter
import com.example.propellerhealthassistant.api.RetrofitInstance
import com.example.propellerhealthassistant.databinding.ActivityMainBinding
import com.example.propellerhealthassistent.model.Event
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import java.io.IOException
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val newEventActivityRequestCode = 1
    private val healthViewModel: HealthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PropellerHealthAssistant_NoActionBar)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = EventListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        setSupportActionBar(binding.toolbar)
        healthViewModel.allEvents.observe(this){ events -> //GET ALL EVENTS AND ADD TO RECYCLERVIEW
            events.let{ adapter.submitList(it) }
        }

        binding.fab.setOnClickListener { view -> //ADD NEW EVENT BUTTON
            val intent = Intent(this@MainActivity, NewEventActivity::class.java)
            startActivityForResult(intent, newEventActivityRequestCode)
        }

        lifecycleScope.launchWhenCreated {
            healthViewModel.getNetworkResponse()
            binding.spinner.visibility = View.GONE //REMOVE SPINNER
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) { //RETRIEVE ADDED EVENT
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newEventActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val event = intentData?.getSerializableExtra("EVENT")
            healthViewModel.insert(event as Event)
        } else {
            Toast.makeText(
                applicationContext,
                "Not Saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}