package com.mqweather.android.weather.utils

import android.util.Log

object MqWeatherLogger {

    //Can be used to diable logging
    var TEST_MODE = false

    fun infoLog(tag: String, msg: String) {
        if (!TEST_MODE) {
            Log.d(tag, msg)
        } else {
            System.out.println(msg)
        }
    }

    fun errLog(tag: String, msg: String, ex: Exception?) {
        if (!TEST_MODE) {
            Log.e(tag, "$msg, Exception : $ex")
        } else {
            System.err.println(msg)
        }
    }
}