package com.mqweather.android.weather.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.mqweather.android.weather.application.MqWeatherApplication.Companion.context
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object AppUtils {

    fun getWidth(context: Context): Int {
        val displayMetrics = DisplayMetrics()
        val windowmanager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowmanager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun convertDPToPixels(dp: Int): Int {
        val scale: Float = context.resources.displayMetrics.density
        // Convert the dps to pixels, based on density scale
        return (dp * scale + 0.5f).toInt()
    }

    fun convertKelvinToCelsius(kelvin: Float): Int {
        return (kelvin - 273.15).toInt()
    }

    //"2020-08-04 18:00:00"
    fun getISO8601TimeStr(date: String) : String {
        val date = SimpleDateFormat("yyyy-MM-dd").parse(date)
        println(date.time)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date.time
        return calendar.get(Calendar.DATE).toString()
    }
}