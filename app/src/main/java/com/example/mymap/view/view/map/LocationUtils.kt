package com.example.mymap.view.view.map

import android.content.Context
import android.location.LocationManager
import java.util.*

object LocationUtils {
    fun formatDms(decimal: Float): String {
        val d = decimal.toLong()
        val m = ((decimal - d) * 60).toLong()
        val s = decimal - d - m / 60 * 3600
        return String.format(Locale.ROOT, "%d°%d'%.2f\"", d, m, s)
    }

    fun isGPSEnabled(context: Context): Boolean {
        val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gpsEnabled = false
        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ignored: Exception) {
            ignored.printStackTrace()
        }
        return gpsEnabled
    }
}