package com.example.propellerhealthassistant.data

import com.example.propellerhealthassistant.api.HealthService
import com.example.propellerhealthassistent.model.Event
import com.example.propellerhealthassistent.model.Medication
import com.example.propellerhealthassistent.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HealthRepository @Inject constructor(private val healthService: HealthService, private val healthDao: HealthDao) {

    val allEvents: Flow<List<Event>> = healthDao.getEvents()

    suspend fun insert(event: Event){
        healthDao.insert(event)
    }

    suspend fun insertMedication(medication: Medication){
        healthDao.insertMedication(medication)
    }

    suspend fun getMedication(){
        val response =  healthService.getHealthData().body()
        insertUser(response?.user!!) // INSERT USER PROFILE

        for(event in response.events){ //INSERT ALL EVENTS
            insert(event)
        }

        for(medication in response.user.medications){ //INSERT MEDICATION LIST
            insertMedication(medication)
        }
    }

    suspend fun insertUser(user: User){
        healthDao.insertUser(user)
    }

    fun getUser(): User{
        return healthDao.getUser()
    }

}