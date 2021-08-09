package com.example.propellerhealthassistant.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.propellerhealthassistant.converters.Converters
import com.example.propellerhealthassistent.model.Event
import com.example.propellerhealthassistent.model.HealthModel
import com.example.propellerhealthassistent.model.Medication
import com.example.propellerhealthassistent.model.User

@Database(entities = arrayOf(
    User::class,
    Medication::class,
    Event::class), version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HealthRoomDatabase: RoomDatabase() {
    abstract fun HealthDao(): HealthDao

    companion object{
        @Volatile
        private var INSTANCE: HealthRoomDatabase? = null
        fun getDatabase(context: Context
        ): HealthRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HealthRoomDatabase::class.java,
                    "health_database"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}