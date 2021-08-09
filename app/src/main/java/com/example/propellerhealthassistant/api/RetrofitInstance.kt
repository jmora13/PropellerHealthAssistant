package com.example.propellerhealthassistant.api

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.Executors

object RetrofitInstance {
    val api: HealthService by lazy{
       Retrofit.Builder()
           .baseUrl("https://s3-us-west-2.amazonaws.com/ph-svc-mobile-interview-jyzi2gyja/")
           .callbackExecutor(Executors.newSingleThreadExecutor())
           .addConverterFactory(JacksonConverterFactory.create())
           .build()
           .create(HealthService::class.java)
    }
}