package com.example.propellerhealthassistant.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.propellerhealthassistent.model.Event
import com.example.propellerhealthassistent.model.Medication
import com.example.propellerhealthassistent.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthDao {
    @Query("SELECT * FROM event ORDER BY datetime")
    fun getEvents(): Flow<List<Event>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMedication(medication: Medication)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM medication")
    fun getMedication(): List<Medication>

    @Query("SELECT * FROM user")
    fun getUser(): User

}