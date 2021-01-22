package com.mqweather.android.weather.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.annotation.Nullable
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import java.util.*
import com.mqweather.android.R
import com.mqweather.android.weather.api.repository.WeatherRepository

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_map)
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)

            if (!Places.isInitialized()) {
                Places.initialize(this, "AIzaSyDATfNfAQuGVjKLMNdQTgRL6COFW_zIgIg");
            }

            val autocompleteFragment =
                supportFragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as AutocompleteSupportFragment?

            autocompleteFragment!!.setPlaceFields(
                listOf(
                    Place.Field.ID,
                    Place.Field.NAME,
                    Place.Field.LAT_LNG
                )
            )

            autocompleteFragment.setOnPlaceSelectedListener(object :
                com.google.android.libraries.places.widget.listener.PlaceSelectionListener {
                override fun onPlaceSelected(place: com.google.android.libraries.places.api.model.Place) {
                    mMap.clear()
                    val cityName = place.name
                    cityName?.let { WeatherRepository.getOnedayWeather(it) }
                    cityName?.let { WeatherRepository.getFiveDaysForecast(it) }

                    mMap.addMarker(
                        MarkerOptions().position(place.latLng!!).title(place.name.toString())
                    )
                    mMap.addMarker(MarkerOptions().position(place.latLng!!).title(place.name.toString()))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(place.latLng))
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.latLng, 12.0f))
                }

                override fun onError(p0: Status) {
                }

            })

        }

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        override fun onMapReady(googleMap: GoogleMap) {
            mMap = googleMap

            // Add a marker in HYD and move the camera
            val sydney = LatLng(17.3850, 78.4867)
            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
}