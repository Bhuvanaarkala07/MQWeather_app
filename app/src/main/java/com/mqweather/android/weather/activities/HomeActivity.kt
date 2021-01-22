package com.mqweather.android.weather.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mqweather.android.R
import com.mqweather.android.weather.adapters.BookmarkAdapter
import com.mqweather.android.weather.database.WeatherResponse
import com.mqweather.android.weather.fragments.BookmarkDetailsFragment
import com.mqweather.android.weather.fragments.HelpInfoFragment
import com.mqweather.android.weather.fragments.MapFragment
import com.mqweather.android.weather.fragments.SettingsFragment
import com.mqweather.android.weather.interfaces.BookmarkTouchListener
import com.mqweather.android.weather.utils.AppUtils
import com.mqweather.android.weather.utils.BOOKMARK
import com.mqweather.android.weather.viewmodel.BookmarkViewModel
import com.mqweather.android.weather.viewmodel.BookmarkViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : AppCompatActivity(), BookmarkTouchListener {

    private val cityName: String = ""
    private lateinit var adapter: BookmarkAdapter
    private var weatherList: MutableList<WeatherResponse> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        //check for location permission
        if (!isLocationPermissionsEnabled()) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                );
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                );
            }
        }

        setListeners()
    }

    private fun setCustomActionbar() {
        setSupportActionBar(toolbar_home)
        val actionBar = supportActionBar
        actionBar?.let {
            actionBar.show()
            actionBar.setTitle(getString(R.string.app_name))
        }
    }

    override fun onResume() {
        super.onResume()
        setCustomActionbar()
    }

    private fun init() {
        adapter = BookmarkAdapter(this, weatherList, this)
        home_bookmark_recycler.adapter = adapter
        home_bookmark_recycler.layoutManager = LinearLayoutManager(this)
        val application = requireNotNull(this).application
        val viewModel = ViewModelProvider(
            this,
            BookmarkViewModelProvider(cityName, application)
        ).get(BookmarkViewModel::class.java)

        viewModel.getWeather()
            ?.observe(this, Observer<MutableList<WeatherResponse>> { weatherList ->
                weatherList?.let {
                    if (!weatherList.isEmpty())
                        home_helptext.visibility = View.GONE
                    adapter.updateList(weatherList)
                }
            })
    }

    private fun setListeners() {
        home_info.setOnClickListener {
            openInfoView()
        }

        home_settings.setOnClickListener {
            openSettings()
        }
    }

    private fun calculateSpanSize(): Int {
        try {
            val width = AppUtils.getWidth(this)
            val dp = AppUtils.convertDPToPixels(100)
            val span = (width / dp)
            return (if (span > 0) span else 2)
        } catch (ex: java.lang.Exception) {
        }
        return 2
    }

    private fun openMapView() {
        createFragment(MapFragment())
    }

    private fun openInfoView() {
        createFragment(HelpInfoFragment())
    }

    private fun openSettings() {
        createFragment(SettingsFragment())
    }

    private fun openDetails(bookmark: WeatherResponse) {
        val fragment = BookmarkDetailsFragment.newInstance(bookmark)
        createFragment(fragment)
    }

    private fun createFragment(mapFragment: Fragment) {
        home_info.visibility = View.GONE
        home_settings.visibility = View.GONE
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment, mapFragment).addToBackStack("tag")
        transaction.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override  fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_map -> {
//                openMapView()
               startActivity(Intent(this,  MapsActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun isLocationPermissionsEnabled(): Boolean {
        return checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                && checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    fun checkSelfPermission(context: Context, permissionType: String): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            permissionType
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onBackPressed() {
        home_info.visibility = View.VISIBLE
        home_settings.visibility = View.VISIBLE
        supportFragmentManager.popBackStack()
        setCustomActionbar()
    }

    override fun onTouch(bookmark: WeatherResponse) {
        openDetails(bookmark)
    }

}