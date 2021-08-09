package com.example.propellerhealthassistent.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@Entity
data class Event(
    @JsonProperty("datetime")
    val datetime: String,
    @PrimaryKey(autoGenerate = true)
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("medication")
    val medication: String,
    @JsonProperty("medicationtype")
    val medicationtype: String,
    @JsonProperty("uid")
    val uid: String
) : Serializable