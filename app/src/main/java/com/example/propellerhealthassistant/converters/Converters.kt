package com.example.propellerhealthassistant.converters

import androidx.room.TypeConverter
import com.example.propellerhealthassistent.model.Event
import com.example.propellerhealthassistent.model.Medication
import com.example.propellerhealthassistent.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
 //TYPE CONVERTER FOR COMPLEX TYPES

    @TypeConverter
    fun restoreMedicationList(medicationList: String?): List<Medication?>? {
        return Gson().fromJson(medicationList, object : TypeToken<List<Medication?>?>() {}.getType())
    }

    @TypeConverter
    fun saveMedicationListOfString(medicationList: List<Medication?>?): String? {
        return Gson().toJson(medicationList)
    }

    @TypeConverter
    fun fromUser(user: User?): String? {
        return if (user == null) null else ""
    }

    @TypeConverter
    fun userFromString(s: String?): User? {
        return if (s == null) null else User()
    }


}