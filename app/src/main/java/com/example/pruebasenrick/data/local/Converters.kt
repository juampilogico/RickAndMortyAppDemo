package com.example.pruebasenrick.data.local


import androidx.room.TypeConverter
import com.example.pruebasenrick.data.Location
import com.example.pruebasenrick.data.Origin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromOrigin(origin: Origin): String = gson.toJson(origin)

    @TypeConverter
    fun toOrigin(data: String): Origin =
        gson.fromJson(data, object : TypeToken<Origin>() {}.type)

    @TypeConverter
    fun fromLocation(location: Location): String = gson.toJson(location)

    @TypeConverter
    fun toLocation(data: String): Location =
        gson.fromJson(data, object : TypeToken<Location>() {}.type)

    @TypeConverter
    fun fromEpisodeList(list: List<String>): String = gson.toJson(list)

    @TypeConverter
    fun toEpisodeList(data: String): List<String> =
        gson.fromJson(data, object : TypeToken<List<String>>() {}.type)
}
