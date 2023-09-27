package com.example.mymap.view.view.map

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.annotation.Nullable
import com.example.mymap.view.view.map.LocationHelper.LocationDataChangeListener


class LocationListener(context: Context) : LocationListener {
    private val mContext: Context

    @Nullable
    private var mLocationValueListener: LocationDataChangeListener? = null
    private var mLoadLocationData: GetDataTask? = null

    init {
        mContext = context
    }

    fun setLocationValueListener(@Nullable listener: LocationDataChangeListener?) {
        mLocationValueListener = listener
    }


    override fun onLocationChanged(location: Location) {
        if (mLocationValueListener != null && location != null) {
            if (mLoadLocationData != null) {
                mLoadLocationData!!.cancel(true)
            }
            mLoadLocationData = GetDataTask(mLocationValueListener!!, mContext)
            mLoadLocationData!!.execute(location)
        }
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}

    companion object {
        private const val TAG = "LocationListener"
    }
}