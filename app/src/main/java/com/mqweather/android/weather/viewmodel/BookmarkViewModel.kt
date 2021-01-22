package com.mqweather.android.weather.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.mqweather.android.weather.api.repository.WeatherRepository
import com.mqweather.android.weather.application.MqWeatherApplication
import com.mqweather.android.weather.database.ForecastRepository
import com.mqweather.android.weather.database.ForecastResponse
import com.mqweather.android.weather.database.WeatherDBRepository
import com.mqweather.android.weather.database.WeatherResponse
import com.mqweather.android.weather.managers.BookmarkManager
import com.mqweather.android.weather.models.Weather
import kotlinx.coroutines.launch
import java.util.jar.Attributes

class BookmarkViewModel(private val cityName : String, application: Application) : AndroidViewModel(application) {

    private var weatherData : LiveData<MutableList<WeatherResponse>>? = MutableLiveData()
    private var forecastData : LiveData<ForecastResponse>? = MutableLiveData()

    private var reposity = WeatherDBRepository()
    private var forecastRepository = ForecastRepository()

    fun getWeather() :  LiveData<MutableList<WeatherResponse>>? {
        weatherData = reposity.getWeatherList()
        return weatherData
    }

    fun getForecast(city : String) : LiveData<ForecastResponse>? {
        forecastData = forecastRepository.getForecastListByCity(city)
        return forecastData
    }

//    fun saveBookmarks() {
//        viewModelScope.launch {
//            val weatherList : MutableList<WeatherResponse> = mutableListOf()
//            val weather  = WeatherResponse()
//            weather.name = "hyd"
//            weather.base = "123"
//            weatherList.add(weather)
//
//            val weather1  = WeatherResponse()
//            weather1.name = "hyd"
//            weather1.base = "123"
//            weatherList.add(weather1)
//
//            val weather2  = WeatherResponse()
//            weather2.name = "hyd"
//            weather2.base = "123"
//            weatherList.add(weather2)
//
//            val weather3  = WeatherResponse()
//            weather3.name = "hyd"
//            weather3.base = "123"
//            weatherList.add(weather3)
//
//            val weather4  = WeatherResponse()
//            weather4.name = "hyd"
//            weather4.base = "123"
//            weatherList.add(weather4)
//
//            val weather5  = WeatherResponse()
//            weather5.name = "hyd"
//            weather5.base = "123"
//            weatherList.add(weather5)
//            reposity.weatherDao?.insert(weatherList)
//
//            getWeather()
//        }
//    }



}