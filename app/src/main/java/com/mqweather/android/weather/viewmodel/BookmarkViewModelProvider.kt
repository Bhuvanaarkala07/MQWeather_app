package com.mqweather.android.weather.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookmarkViewModelProvider(
    private val city: String,
    private val application: Application
) :  ViewModelProvider.Factory{

    override fun <Any : ViewModel?> create(modelClass: Class<Any>): Any {
        return BookmarkViewModel (city, application) as Any
    }

}