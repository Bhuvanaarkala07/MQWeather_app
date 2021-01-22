package com.mqweather.android.weather.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(
        WeatherResponse::class,
ForecastResponse::class), version = 1)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
    abstract fun forecastDao(): ForecastDao
}