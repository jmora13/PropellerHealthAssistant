package com.example.propellerhealthassistant.di

import com.example.propellerhealthassistant.api.HealthService
import com.example.propellerhealthassistant.data.HealthDao
import com.example.propellerhealthassistant.data.HealthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesRepo(healthService: HealthService, healthDao: HealthDao): HealthRepository {
        return HealthRepository(healthService, healthDao)
    }
}