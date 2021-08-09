package com.example.propellerhealthassistant.data

import com.example.propellerhealthassistent.model.Event
import com.example.propellerhealthassistent.model.Medication
import com.example.propellerhealthassistent.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HealthRepository @Inject constructor(private val healthDao: HealthDao) {

    val allEvents: Flow<List<Event>> = healthDao.getEvents()

    suspend fun insert(event: Event){
        healthDao.insert(event)
    }

    suspend fun insertMedication(medication: Medication){
        healthDao.insertMedication(medication)
    }

    fun getMedication(): List<Medication>{
        return healthDao.getMedication()
    }

    suspend fun insertUser(user: User){
        healthDao.insertUser(user)
    }

    fun getUser(): User{
        return healthDao.getUser()
    }

}