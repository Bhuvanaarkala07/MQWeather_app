package com.mqweather.android.weather.database

import androidx.lifecycle.LiveData
import com.mqweather.android.weather.application.MqWeatherApplication

class ForecastRepository {

    var forecastDao : ForecastDao? = null

    init{
        forecastDao = MqWeatherApplication.database?.forecastDao()
    }

    suspend fun saveForecast(weather: ForecastResponse){
        forecastDao?.insert(weather)
    }

    fun getForecastList() :  LiveData<MutableList<ForecastResponse>>? {
        return forecastDao?.getForecastList()
    }

    fun getForecastListByCity(name : String) :  LiveData<ForecastResponse>? {
        return forecastDao?.getForecastCity(name)
    }


}