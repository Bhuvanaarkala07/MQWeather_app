package com.mqweather.android.weather.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mqweather.android.weather.database.converters.WeatherConverter
import com.mqweather.android.weather.models.Coords
import com.mqweather.android.weather.models.MainWeather
import com.mqweather.android.weather.models.Weather
import java.io.Serializable
import java.util.*

@Entity
class WeatherResponse  : Serializable{

    @PrimaryKey(autoGenerate = true)
    var id : Long? = UUID.randomUUID().mostSignificantBits
    @Embedded
    var coord: Coords? = null
    @TypeConverters(WeatherConverter::class)
    var weather: List<Weather>? = mutableListOf()
    var base: String? = null
    @Embedded
    var main: MainWeather? = null
    var name: String? = null
    var dt_txt: String? = null
}