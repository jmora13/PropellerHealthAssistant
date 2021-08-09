package com.example.propellerhealthassistant

import android.util.Log
import androidx.lifecycle.*
import com.example.propellerhealthassistant.api.HealthService
import com.example.propellerhealthassistant.api.RetrofitInstance
import com.example.propellerhealthassistant.data.HealthRepository
import com.example.propellerhealthassistent.model.Event
import com.example.propellerhealthassistent.model.Medication
import com.example.propellerhealthassistent.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HealthViewModel @Inject constructor(private val repository: HealthRepository): ViewModel() {

    @Inject
    lateinit var healthService: HealthService
    val allEvents: LiveData<List<Event>> = repository.allEvents.asLiveData()
    //val allMedication: LiveData<List<Medication>> = repository.allMedication.asLiveData()

    fun insert(event: Event) = viewModelScope.launch{
        repository.insert(event)
    }

    fun insertMedication(medication: Medication) = viewModelScope.launch{
        repository.insertMedication(medication)
    }

    fun insertUser(user: User) = viewModelScope.launch{
        repository.insertUser(user)
    }

    fun getMedication(): List<Medication>{
        return repository.getMedication()
    }

    fun getUser(): User{
        return repository.getUser()
    }

    suspend fun getNetworkResponse(){
        val response = healthService.getHealthData().body()

        insertUser(response!!.user) // INSERT USER PROFILE

        for(event in response!!.events!!){ //INSERT ALL EVENTS
            insert(event)
        }

        for(medication in response!!.user.medications){ //INSERT MEDICATION LIST
            insertMedication(medication)
        }
    }

}

