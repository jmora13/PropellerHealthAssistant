package com.example.propellerhealthassistant.di

import com.example.propellerhealthassistant.api.HealthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.Executors
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideHealthService(): HealthService {
        return Retrofit.Builder()
            .baseUrl("https://s3-us-west-2.amazonaws.com/ph-svc-mobile-interview-jyzi2gyja/")
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(HealthService::class.java)
    }
}