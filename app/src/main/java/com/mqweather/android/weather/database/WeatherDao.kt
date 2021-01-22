package com.mqweather.android.weather.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao : BaseDao<WeatherResponse> {

    @Query("SELECT * FROM WeatherResponse")
    fun getWeatherList(): LiveData<MutableList<WeatherResponse>>

    @Query("SELECT * FROM WeatherResponse where id LIKE :id")
    fun getWeatherID(id : String?): WeatherResponse

    @Query("DELETE from WeatherResponse")
    fun deleteWeather()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(response: List<WeatherResponse>)

    @Query("DELETE from WeatherResponse")
    suspend fun deleteWeatherResponse()
}