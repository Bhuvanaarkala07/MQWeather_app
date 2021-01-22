package com.mqweather.android.weather.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mqweather.android.weather.models.Weather

object WeatherConverter{

    @TypeConverter
    @JvmStatic
    fun fromAppInfoModelList(weatherResponse: List<Weather>?): String? {
        if (weatherResponse == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Weather>>() {

        }.getType()
        return gson.toJson(weatherResponse, type)
    }

    @TypeConverter @JvmStatic
    fun toAppInfoModelList(weatherResponse: String?): List<Weather>? {
        if (weatherResponse == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Weather>>() {

        }.getType()
        return gson.fromJson<List<Weather>>(weatherResponse, type)
    }
}