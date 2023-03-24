package com.hey.rickandmortyappexample.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromListEpisodeToString(location: MutableList<String>?): String? {
        if (location == null) {
            return null
        }
        return Gson().toJson(location, object : TypeToken<MutableList<String>>(){}.type)
    }

    @TypeConverter
    fun fromStringToListEpisode(string: String) : MutableList<String>?{
        if (string.isEmpty()){
            return null
        }
        return Gson().fromJson(string, object : TypeToken<MutableList<String>>(){}.type)
    }
}