package com.mqweather.android.weather.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mqweather.android.weather.database.converters.ForecastConverter
import com.mqweather.android.weather.models.City
import java.util.*

@Entity
class ForecastResponse {

    @PrimaryKey(autoGenerate = true)
    var id : Long? = UUID.randomUUID().mostSignificantBits

    @TypeConverters(ForecastConverter::class)
    var list : List<WeatherResponse> = mutableListOf()

   var name: String? = null

}