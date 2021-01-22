package com.mqweather.android.weather.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mqweather.android.R
import com.mqweather.android.weather.database.WeatherDBRepository
import com.mqweather.android.weather.database.WeatherResponse
import com.mqweather.android.weather.interfaces.BookmarkTouchListener
import com.mqweather.android.weather.utils.AppUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookmarkAdapter(
    private val context: Context,
    bookmarks: MutableList<WeatherResponse>,
    private val listener: BookmarkTouchListener
) : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

    private var bookMarksLsit = mutableListOf<WeatherResponse>()
    private val reposity = WeatherDBRepository()

    init {
        bookMarksLsit = bookmarks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.bookmark_grid_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (bookMarksLsit.isNullOrEmpty()) 0 else bookMarksLsit.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookmark = bookMarksLsit.get(position)
        holder.name.text = bookmark.name
        holder.temp.text =
            bookmark.main?.temp?.toFloat()?.let { AppUtils.convertKelvinToCelsius(it).toString() } + "c"
        holder.mainLayout.setOnClickListener {
            listener.onTouch(bookmark)
        }

        val weatherMain = bookmark.weather?.get(0)
        weatherMain?.let {
            holder.weatherMain.text = weatherMain.main

            val url = "http://openweathermap.org/img/w/" + weatherMain.icon + ".png"
            Glide.with(context)
                .load(url)
                .into(holder.icon)
        }

        holder.deleteIcon.setOnClickListener {
            bookMarksLsit.removeAt(position)
            GlobalScope.launch {
                reposity.delete(bookmark)
            }
            notifyDataSetChanged()
        }

    }

    fun updateList(weatherList: MutableList<WeatherResponse>) {
        this.bookMarksLsit = weatherList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.city_name)
        var mainLayout = view.findViewById<ConstraintLayout>(R.id.grid_layout)
        var temp = view.findViewById<TextView>(R.id.temp_tv)
        var icon = view.findViewById<ImageView>(R.id.icon)
        var weatherMain = view.findViewById<TextView>(R.id.weather_main)
        val deleteIcon = view.findViewById<ImageView>(R.id.bookmark_delete)
    }

}