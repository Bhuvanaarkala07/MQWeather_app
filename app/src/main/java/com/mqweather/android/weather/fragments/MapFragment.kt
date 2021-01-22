package com.mqweather.android.weather.fragments

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.mqweather.android.R


class MapFragment : BaseFragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val AUTOCOMPLETE_REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = activity?.supportFragmentManager
            ?.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        if (!Places.isInitialized()) {
            context?.let { Places.initialize(it, "AIzaSyDATfNfAQuGVjKLMNdQTgRL6COFW_zIgIg") };
        }

        val autocompleteFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.place_autocomplete_fragment) as? AutocompleteSupportFragment

        autocompleteFragment?.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG
            )
        )

        autocompleteFragment?.setOnPlaceSelectedListener(object :
            com.google.android.libraries.places.widget.listener.PlaceSelectionListener {
            override fun onPlaceSelected(place: com.google.android.libraries.places.api.model.Place) {
                mMap.clear()
                val fields: List<Place.Field> = listOf(Place.Field.ID, Place.Field.NAME)
                showShortToast(place.name.toString())
                showShortToast(""+place.latLng)

                mMap.addMarker(
                    MarkerOptions().position(place.latLng!!).title(place.name.toString())
                )
                mMap.addMarker(MarkerOptions().position(place.latLng!!).title(place.name.toString()))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place.latLng))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.latLng, 12.0f))
            }

            override fun onError(p0: Status) {
                showLongToast(p0.toString())
            }

        })
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in HYD and move the camera
        val sydney = LatLng(17.3850, 78.4867)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
//        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
//            when (resultCode) {
//                RESULT_OK -> {
//                    val place: com.google.android.libraries.places.api.model.Place =
//                        Autocomplete.getPlaceFromIntent(
//                            data!!
//                        )
//                    showLongToast(place.name!!)
//                }
//                AutocompleteActivity.RESULT_ERROR -> {
//                    val status = Autocomplete.getStatusFromIntent(data!!)
//                    showLongToast("Err - ${status.status}")
//
//                }
//                RESULT_CANCELED -> {
//                    // The user canceled the operation.
//                }
//            }
//            return
//        }
//        super.onActivityResult(requestCode, resultCode, data)
//    }


}