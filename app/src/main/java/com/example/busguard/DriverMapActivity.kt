package com.example.busguard

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.busGuard.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DriverMapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        private const val TAG = "DriverMapActivity"
        private const val DEFAULT_ZOOM = 15f
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    }

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var lastKnownLocation: Location
    private var locationPermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Check if location permission is granted
        getLocationPermission()

        // Update location and move camera to current location
        updateLocationUI()

        // Get the current location of the device
        getDeviceLocation()
    }

    private fun getLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        locationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true
                }
            }
        }
        updateLocationUI()
    }

    private fun updateLocationUI() {
        try {
            if (locationPermissionGranted) {
                mMap.isMyLocationEnabled = true
                mMap.uiSettings.isMyLocationButtonEnabled = true
            } else {
                mMap.isMyLocationEnabled = false
                mMap.uiSettings.isMyLocationButtonEnabled = false
                val also = LatLng(/* latitude = */ 0.0, /* longitude = */ 0.0).also {
                    lastKnownLocation = mMap.myLocation
                }
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Exception: ${e.message}")
        }
    }

    private fun getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                LatLng(lastKnownLocation.latitude,
                                    lastKnownLocation.longitude), DEFAULT_ZOOM))
                        }
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.")
                        Log.e(TAG, "Exception: ${task.exception}")
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                            LatLng(0.0, 0.0), DEFAULT_ZOOM))
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Exception: ${e.message}")
        }
    }
}