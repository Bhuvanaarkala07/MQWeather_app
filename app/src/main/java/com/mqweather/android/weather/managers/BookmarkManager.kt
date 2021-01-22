package com.mqweather.android.weather.managers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mqweather.android.weather.database.WeatherDBRepository
import com.mqweather.android.weather.database.WeatherResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object BookmarkManager {

    private var weatherData : LiveData<MutableList<WeatherResponse>>? = MutableLiveData()

    private var reposity = WeatherDBRepository()

    fun getWeather() :  LiveData<MutableList<WeatherResponse>>? {
        weatherData = reposity.getWeatherList()
        return weatherData
    }

    fun deleteWeather() {
        GlobalScope.launch {
            reposity.delete()
        }
    }

    suspend fun saveBookmarks() {

        val weatherList : MutableList<WeatherResponse> = mutableListOf()
        val weather  = WeatherResponse()
        weather.name = "hyd"
        weather.base = "123"
        weatherList.add(weather)

        val weather1  = WeatherResponse()
        weather1.name = "hyd"
        weather1.base = "123"
        weatherList.add(weather1)

        val weather2  = WeatherResponse()
        weather2.name = "hyd"
        weather2.base = "123"
        weatherList.add(weather2)

        val weather3  = WeatherResponse()
        weather3.name = "hyd"
        weather3.base = "123"
        weatherList.add(weather3)

        val weather4  = WeatherResponse()
        weather4.name = "hyd"
        weather4.base = "123"
        weatherList.add(weather4)

        val weather5  = WeatherResponse()
        weather5.name = "hyd"
        weather5.base = "123"
        weatherList.add(weather5)


        reposity.weatherDao?.insert(weatherList)
    }

}