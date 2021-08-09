package com.example.propellerhealthassistant.api

import com.example.propellerhealthassistent.model.HealthModel
import retrofit2.Response
import retrofit2.http.GET

interface HealthService {

    @GET("propeller_mobile_assessment_data.json")
    suspend fun getHealthData(): Response<HealthModel>
}