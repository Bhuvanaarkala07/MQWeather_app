package com.mqweather.android.weather.interfaces

import com.mqweather.android.weather.database.WeatherResponse

interface BookmarkTouchListener {

    fun onTouch(bookmark: WeatherResponse)

}