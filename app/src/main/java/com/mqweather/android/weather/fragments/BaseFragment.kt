package com.mqweather.android.weather.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    fun showLongToast(message: String?) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        } catch (ex: Exception) {
        }
    }

    fun showShortToast(message: String?) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        } catch (ex: Exception) {
        }
    }
}

