package com.mqweather.android.weather.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mqweather.android.R
import com.mqweather.android.weather.database.ForecastResponse
import com.mqweather.android.weather.database.WeatherResponse
import com.mqweather.android.weather.utils.AppUtils
import com.mqweather.android.weather.utils.BOOKMARK
import com.mqweather.android.weather.viewmodel.BookmarkViewModel
import com.mqweather.android.weather.viewmodel.BookmarkViewModelProvider
import kotlinx.android.synthetic.main.fragment_details.view.*
import java.util.*
import androidx.lifecycle.Observer

class BookmarkDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance(bookmark: WeatherResponse): BookmarkDetailsFragment {
            val fragment = BookmarkDetailsFragment()

            val bundle = Bundle().apply {
                putSerializable(BOOKMARK, bookmark)
            }

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        val bookmark = arguments?.getSerializable(BOOKMARK) as? WeatherResponse
        bookmark?.let {
            (activity as AppCompatActivity).supportActionBar?.hide()
            view.bookmark_home.text = it.name.toString()
            view.bookmark_date_tv.text = Date().toString()

            view.bookmark_temp_tv.text =
                it.main?.feels_like?.toFloat()?.let { it1 -> AppUtils.convertKelvinToCelsius(it1).toString() } +"c"

            view.bookmark_mintemp_tv.text =  "Min temp " + it.main?.temp_min?.toFloat()?.let { it1 ->
                AppUtils.convertKelvinToCelsius(
                    it1
                ).toString()
            }
            view.bookmark_maxtemp_tv.text =  "Max temp " + it.main?.temp_max?.toFloat()?.let { it1 ->
                AppUtils.convertKelvinToCelsius(
                    it1
                ).toString()
            }
            view.bookmark_pressure_tv.text =  "Pressure " + it.main?.pressure?.toFloat()?.let { it1 ->
                AppUtils.convertKelvinToCelsius(
                    it1
                ).toString()
            }
            view.bookmark_humidity_tv.text = "Humidity " + it.main?.humidity?.toFloat()?.let { it1 ->
                AppUtils.convertKelvinToCelsius(
                    it1
                ).toString()
            }
        }

        val application = requireNotNull(activity).application
        val viewModel = ViewModelProvider(
            this,
            BookmarkViewModelProvider("cityName", application)
        ).get(BookmarkViewModel::class.java)

        bookmark?.name?.let {
            viewModel.getForecast(it)
                ?.observe(viewLifecycleOwner, Observer<ForecastResponse> { forecastResponse ->
                    forecastResponse?.let {
                        val forecastList = forecastResponse.list
                        val forecast = forecastList.get(0)
                            view.bookmark_week1_temp_tv.text =
                                forecast.main?.temp?.toFloat()?.let { it1 ->
                                    AppUtils.convertKelvinToCelsius(
                                        it1
                                    ).toString()
                                }
                            view.bookmark_week1_day_tv.text =
                                forecast.dt_txt?.let { it1 -> AppUtils.getISO8601TimeStr(it1) }  //2020-08-04 18:00:00

                        val forecast1 = forecastList.get(1)
                        view.bookmark_week2_temp_tv.text =
                            forecast1.main?.temp?.toFloat()?.let { it1 ->
                                AppUtils.convertKelvinToCelsius(
                                    it1
                                ).toString()
                            }
                        view.bookmark_week2_day_tv.text = forecast1.dt_txt?.let { it1 ->
                            AppUtils.getISO8601TimeStr(
                                it1
                            )
                        }

                        val forecast2 = forecastList.get(2)
                        view.bookmark_week3_temp_tv.text =
                            forecast2.main?.temp?.toFloat()?.let { it1 ->
                                AppUtils.convertKelvinToCelsius(
                                    it1
                                ).toString()
                            }
                        view.bookmark_week3_day_tv.text = forecast2.dt_txt?.let { it1 ->
                            AppUtils.getISO8601TimeStr(
                                it1
                            )
                        }

                        val forecast3 = forecastList.get(2)
                        view.bookmark_week4_temp_tv.text =
                            forecast3.main?.temp?.toFloat()?.let { it1 ->
                                AppUtils.convertKelvinToCelsius(
                                    it1
                                ).toString()
                            }
                        view.bookmark_week4_day_tv.text = forecast3.dt_txt?.let { it1 ->
                            AppUtils.getISO8601TimeStr(
                                it1
                            )
                        }

                        val forecast4 = forecastList.get(3)
                        view.bookmark_week5_temp_tv.text =
                            forecast4.main?.temp?.toFloat()?.let { it1 ->
                                AppUtils.convertKelvinToCelsius(
                                    it1
                                ).toString()
                            }
                        view.bookmark_week5_day_tv.text = forecast4.dt_txt?.let { it1 ->
                            AppUtils.getISO8601TimeStr(
                                it1
                            )
                        }
                    }
                })
        }

        return view
    }


}