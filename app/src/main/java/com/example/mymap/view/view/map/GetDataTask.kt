package com.example.mymap.view.view.map

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.AsyncTask
import com.example.mymap.view.view.map.LocationHelper.LocationDataChangeListener
import java.util.*


class GetDataTask(
    private val mLocationValueListener: LocationDataChangeListener,
    context: Context
) :
    AsyncTask<Location?, Any?, LocationData?>() {
    private val mContext: Context

    init {
        mContext = context
    }

    override fun onPostExecute(locationData: LocationData?) {
        super.onPostExecute(locationData)
        mLocationValueListener.onUpdateLocationData(locationData)
    }

    companion object {
        private const val TAG = "LoadLocationDataTask"
    }

    override fun doInBackground(vararg params: Location?): LocationData? {
        val location: Location = params[0]!!
        val longitude: Double = location.getLongitude()
        val latitude: Double = location.getLatitude()
        val locationData = LocationData()
        locationData.longitude = longitude.toFloat()
        locationData.latitude = latitude.toFloat()
        locationData.altitude = location.getAltitude()
        try {
            val geocoder = Geocoder(mContext, Locale.getDefault())
            val addresses: List<Address>? =
                geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1)
            if (addresses!!.size > 0) {
                val address: Address = addresses[0]
                locationData.address = address
                if (address.getThoroughfare() != null && address.getAdminArea() != null) {
                    locationData.addressLine =
                        address.getThoroughfare() + "," + address.getAdminArea()
                } else if (address.getFeatureName() != null && address.getAdminArea() != null) {
                    locationData.addressLine =
                        address.getFeatureName() + "," + address.getAdminArea()
                } else {
                    locationData.addressLine = ""
                }
            }
        } catch (e: Exception) {
            locationData.addressLine = ""
            return null
        }
        return locationData
    }
}