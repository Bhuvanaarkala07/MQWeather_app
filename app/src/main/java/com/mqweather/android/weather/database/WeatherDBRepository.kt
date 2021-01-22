package com.mqweather.android.weather.database

import androidx.lifecycle.LiveData
import com.mqweather.android.weather.application.MqWeatherApplication

class WeatherDBRepository {

    var weatherDao : WeatherDao? = null

    init{
        weatherDao = MqWeatherApplication.database?.weatherDao()
    }

    suspend fun saveWeather(weather: WeatherResponse){
            weatherDao?.insert(weather)
    }

    fun getWeatherList() :  LiveData<MutableList<WeatherResponse>>? {
        return weatherDao?.getWeatherList()
    }

    suspend fun delete(weather: WeatherResponse){
        weatherDao?.delete(weather)
    }

    suspend fun delete(){
        weatherDao?.deleteWeatherResponse()
    }



}