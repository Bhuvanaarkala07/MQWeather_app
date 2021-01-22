package com.mqweather.android.weather.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mqweather.android.weather.database.WeatherResponse
import com.mqweather.android.weather.models.Weather

object ForecastConverter{

    @TypeConverter
    @JvmStatic
    fun fromAppInfoModelList(weatherResponse: List<WeatherResponse>?): String? {
        if (weatherResponse == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<WeatherResponse>>() {

        }.getType()
        return gson.toJson(weatherResponse, type)
    }

    @TypeConverter @JvmStatic
    fun toAppInfoModelList(weatherResponse: String?): List<WeatherResponse>? {
        if (weatherResponse == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<WeatherResponse>>() {

        }.getType()
        return gson.fromJson<List<WeatherResponse>>(weatherResponse, type)
    }
}