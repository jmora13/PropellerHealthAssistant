package com.example.propellerhealthassistant.di

import android.content.Context
import com.example.propellerhealthassistant.data.HealthDao
import com.example.propellerhealthassistant.data.HealthRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context?): HealthRoomDatabase {
        return HealthRoomDatabase.getDatabase(context!!)
    }

    @Provides
    @Singleton
    fun provideHealthDao(healthDatabase: HealthRoomDatabase): HealthDao {
        return healthDatabase.HealthDao()
    }
}