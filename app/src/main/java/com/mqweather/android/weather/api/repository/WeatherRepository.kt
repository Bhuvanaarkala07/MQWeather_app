package com.mqweather.android.weather.api.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.flickr.gallery.android.api.http.HTTPClient
import com.flickr.gallery.android.api.http.IAPIService
import com.mqweather.android.weather.database.WeatherResponse
import com.mqweather.android.weather.application.MqWeatherApplication.Companion.context
import com.mqweather.android.weather.database.ForecastRepository
import com.mqweather.android.weather.database.ForecastResponse
import com.mqweather.android.weather.database.WeatherDBRepository
import com.mqweather.android.weather.utils.MqWeatherLogger
import com.mqweather.android.weather.utils.OPEN_WEATHER_MAP_APPID
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WeatherRepository {

    private val tag = WeatherRepository::class.java.simpleName
    private val reposity = WeatherDBRepository()
    private val forecastRepository = ForecastRepository()

    fun getOnedayWeather(cityName : String): MutableLiveData<WeatherResponse>? {
        try {
            val apiService: IAPIService =
                HTTPClient.getHttpClient()!!.create(IAPIService::class.java)  // Get API service
            val todaysForecast = apiService.getTodaysWeather(cityName, OPEN_WEATHER_MAP_APPID)
            todaysForecast.enqueue(
                object : Callback<WeatherResponse> {
                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        MqWeatherLogger.errLog(tag, "onFailure() :: Failed to get response ", null);
                    }

                    override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                        val result = response.body() as WeatherResponse
                        if (result != null){
                            GlobalScope.launch {
                                reposity.saveWeather(result)
                            }
                        } else {
                            showApiErrorMessage(response)
                        }
                    }
                }
            )
        } catch (ex: Exception) {
            MqWeatherLogger.errLog(tag, "getOnedayForecast() :: exception while getting from server ", ex);
        }
        return null
    }

    fun getFiveDaysForecast(cityName : String): List<ForecastResponse>? {
        try {
            val apiService: IAPIService =
                HTTPClient.getHttpClient()!!.create(IAPIService::class.java)  // Get API service
            val todaysForecast = apiService.getFivedaysForecast(cityName, OPEN_WEATHER_MAP_APPID)
            todaysForecast.enqueue(
                object : Callback<ForecastResponse> {
                    override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                        MqWeatherLogger.errLog(tag, "onFailure() :: Failed to get response ", null);
                    }

                    override fun onResponse(call: Call<ForecastResponse>, response: Response<ForecastResponse>) {
                        val result = response.body() as ForecastResponse
                        if (result != null){
                            GlobalScope.launch {
                                result.name = cityName
                                forecastRepository.saveForecast(result)
                            }
                        } else {
                            showApiErrorMessage(response)
                        }
                    }
                }
            )
        } catch (ex: Exception) {
            MqWeatherLogger.errLog(tag, "getFiveDaysForecast() :: exception while getting from server ", ex);
        }
        return null
    }

    private fun showApiErrorMessage(response: Response<*>) {
        try {
            val json = response.errorBody()?.string()
           Toast.makeText(context, "Failed to get response", Toast.LENGTH_SHORT).show()
        } catch (e: Throwable) {
            Toast.makeText(context, "Failed to get response", Toast.LENGTH_SHORT).show()
        }
    }
}