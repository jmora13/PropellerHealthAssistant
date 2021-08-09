package com.example.propellerhealthassistent.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity
data class Medication(
    @JsonProperty("medicationtype")
    val medicationtype: String,
    @PrimaryKey
    @JsonProperty("name")
    val name: String
)