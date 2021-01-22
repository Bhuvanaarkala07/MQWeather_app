package com.mqweather.android

import com.flickr.gallery.android.api.http.HTTPClient
import com.flickr.gallery.android.api.http.IAPIService
import com.mqweather.android.weather.database.WeatherResponse
import com.mqweather.android.weather.utils.MqWeatherLogger.TEST_MODE
import com.mqweather.android.weather.utils.OPEN_WEATHER_MAP_APPID
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    init {
        TEST_MODE = true
        HTTPClient.initialize()
    }

    @Test
    fun canHitWeatherEndPoint() {
        val response = hitWeatherEndPoint()
        Assert.assertEquals(response?.code(), 200)
    }

    @Test
    fun canGetResponseFromServerForWeather() {
        val response = hitWeatherEndPoint()
        Assert.assertNotNull(response?.body())
    }

    @Test
    fun canGetResponseFromServerForForecast() {
        val response = hitForecastEndPoint()
        Assert.assertNotNull(response?.body())
    }

    private fun hitWeatherEndPoint(): retrofit2.Response<WeatherResponse>? {
        val apiService: IAPIService? =
            HTTPClient.getHttpClient()?.create(IAPIService::class.java)  // Get API service
        val response = apiService?.getTodaysWeather("Hyderabad", OPEN_WEATHER_MAP_APPID)
        return response?.execute()
    }

    private fun hitForecastEndPoint(): retrofit2.Response<WeatherResponse>? {
        val apiService: IAPIService? =
            HTTPClient.getHttpClient()?.create(IAPIService::class.java)  // Get API service
        val response = apiService?.getFivedaysForecast("Hyderabad", OPEN_WEATHER_MAP_APPID)
        return response?.execute()
    }
}