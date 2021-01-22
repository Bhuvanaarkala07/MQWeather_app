package com.flickr.gallery.android.api.http

import com.mqweather.android.weather.database.ForecastResponse
import com.mqweather.android.weather.database.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API service interface to call APIs from consume server APIs.
 */

interface IAPIService {

    /**
     * Reads one day forecast
     */
    @GET(BASE_URL+"/data/2.5/weather")
    fun getTodaysWeather(@Query("q") city: String,
                          @Query("APPID") appid: String
    ): Call<WeatherResponse>



    @GET(BASE_URL+"/data/2.5/forecast")
    fun getFivedaysForecast(@Query("q") city: String,
                          @Query("APPID") appid: String
    ): Call<ForecastResponse>
}

