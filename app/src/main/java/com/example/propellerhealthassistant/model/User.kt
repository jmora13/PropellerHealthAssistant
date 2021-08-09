package com.example.propellerhealthassistent.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity
data class User(
    @JsonProperty("address1")
    val address1: String,
    @JsonProperty("address2")
    val address2: String,
    @JsonProperty("disease")
    val disease: String,
    @JsonProperty("dob")
    val dob: String,
    @JsonProperty("medications")
    val medications: List<Medication>,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("sex")
    val sex: String,
    @PrimaryKey
    @JsonProperty("uid")
    val uid: String
) {
    constructor() : this("","","","",listOf<Medication>(),"","","")
}