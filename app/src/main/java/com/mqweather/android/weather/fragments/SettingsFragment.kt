package com.mqweather.android.weather.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.mqweather.android.R
import com.mqweather.android.weather.interfaces.IOnBackPressed
import com.mqweather.android.weather.managers.BookmarkManager
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : BaseFragment(), IOnBackPressed {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.settings)

        view.app_version.text = """${getString(R.string.app_version)} - ${getAppVersion()}"""

        view.reset_bookmarks.setOnClickListener {
            BookmarkManager.deleteWeather()
        }
        return view
    }

    fun getAppVersion(): String {
        var appVersion: String? = null

        try {
            val packageManager = context?.packageManager
            val packageInfo =
                context?.packageName?.let { packageManager?.getPackageInfo(it, 0) }
            appVersion = packageInfo?.versionName
        } catch (var4: Exception) {
        }

        return appVersion.toString()
    }

    override fun onBackPressed(): Boolean {
        return true
    }

}