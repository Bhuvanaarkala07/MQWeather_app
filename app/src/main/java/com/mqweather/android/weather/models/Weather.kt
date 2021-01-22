package com.mqweather.android.weather.models

import androidx.room.Entity

@Entity
class Weather {
    var main : String = ""
    var description: String = ""
    var icon: String = ""
}