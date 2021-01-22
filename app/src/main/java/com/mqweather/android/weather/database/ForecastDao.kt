package com.mqweather.android.weather.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ForecastDao : BaseDao<ForecastResponse> {

    @Query("SELECT * FROM ForecastResponse")
    fun getForecastList(): LiveData<MutableList<ForecastResponse>>

    @Query("SELECT * FROM ForecastResponse where name LIKE :name")
    fun getForecastCity(name : String?): LiveData<ForecastResponse>

    @Query("DELETE from ForecastResponse")
    fun deleteForecast()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(response: List<ForecastResponse>)
}