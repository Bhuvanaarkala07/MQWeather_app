package com.mqweather.android.weather.application

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.mqweather.android.weather.dagger.DaggerAppComponent
import com.mqweather.android.weather.database.RoomDatabase

class MqWeatherApplication : Application() {

    companion object {
        var database: RoomDatabase? = null
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        database =  Room.databaseBuilder(this, RoomDatabase::class.java, "weather_db")
            .build()
    }

}