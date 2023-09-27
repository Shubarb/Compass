package com.example.mymap.view.view.map

import android.Manifest
import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient


class LocationHelper(activity: Activity) {
    private val mContext: Context
    private val activity: Activity

    @Nullable
    private var mLocationListener: LocationListener? = null

    @Nullable
    private var mLocationValueListener: LocationDataChangeListener? = null

    init {
        mContext = activity
        this.activity = activity
    }

    @SuppressLint("MissingPermission")
    fun onCreate() {
        if (permissionGranted()) {
            val locationManager =
                mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            mLocationListener = LocationListener(mContext)
            mLocationListener!!.setLocationValueListener(mLocationValueListener)
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                10f,
                mLocationListener!!
            )
            val client: FusedLocationProviderClient = getFusedLocationProviderClient(mContext)
            client.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    mLocationListener!!.onLocationChanged(location)
                }
            }
        } else {
            requestPermission()
        }
    }

    private fun permissionGranted(): Boolean {
        return (ContextCompat.checkSelfPermission(
            mContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
                == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
            mContext,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
                == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(
                arrayOf<String>(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), REQUEST_CODE
            )
        }
    }

//    fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String?>?,
//        grantResults: IntArray
//    ) {
//        if (grantResults.size == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(mContext, R.string.permission_deny, Toast.LENGTH_SHORT).show()
//        } else {
//            onCreate()
//        }
//    }

    fun setLocationValueListener(locationValueListener: LocationDataChangeListener?) {
        mLocationValueListener = locationValueListener
        if (mLocationListener != null) {
            mLocationListener!!.setLocationValueListener(locationValueListener)
        }
    }

    interface LocationDataChangeListener {
        fun onUpdateLocationData(locationData: LocationData?)
    }

    companion object {
        private const val TAG = "LocationHelper"
        private const val REQUEST_CODE = 1111
    }
}