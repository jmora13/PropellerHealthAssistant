package com.example.propellerhealthassistant

import android.app.Application
import com.example.propellerhealthassistant.data.HealthRepository
import com.example.propellerhealthassistant.data.HealthRoomDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HealthApplication : Application() {
    val database by lazy { HealthRoomDatabase.getDatabase(this)}
    val repository by lazy{ HealthRepository(database.HealthDao()) }

}