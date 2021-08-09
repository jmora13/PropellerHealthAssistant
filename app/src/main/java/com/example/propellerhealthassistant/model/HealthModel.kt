package com.example.propellerhealthassistent.model



import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

data class HealthModel(
    @JsonProperty("events")
    val events: List<Event>,
    @PrimaryKey
    @JsonProperty("user")
    val user: User
)