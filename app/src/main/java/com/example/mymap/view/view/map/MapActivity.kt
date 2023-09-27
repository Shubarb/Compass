package com.example.mymap.view.view.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.mymap.R
import com.example.mymap.databinding.ActivityMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : AppCompatActivity(), SensorEventListener, OnMapReadyCallback {
    private var mSensorManager: SensorManager? = null
    private var currentDegrree = 0f
    private lateinit var binding: ActivityMapBinding
    private lateinit var mapFragment: SupportMapFragment
    private var mMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var currentLocation: Location
    private val permissionCode= 123
    private var mLocationHelper: LocationHelper? = null
    private var latitude: Float = 0f
    private var longitude: Float = 0f
    private var addressStr: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map)
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mLocationHelper = LocationHelper(this)

        getCurrentLocation()

    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            mapFragment = supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
            mapFragment.getMapAsync{ map ->
                mMap = map
            }
        }

        mLocationHelper?.setLocationValueListener(object : LocationHelper.LocationDataChangeListener {
            override fun onUpdateLocationData(locationData: LocationData?) {
                if (locationData != null) {
                    //address
                    addressStr = locationData.addressLine ?: ""
                    //location
                    latitude = locationData.latitude
                    longitude = locationData.longitude
                    Toast.makeText(applicationContext,"$latitude  $longitude",Toast.LENGTH_SHORT).show()
                    if (ActivityCompat.checkSelfPermission(applicationContext,
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(applicationContext,
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        fusedLocationClient.lastLocation
                            .addOnSuccessListener { location ->
                                if (location != null) {
                                    // Lấy vị trí thành công, sử dụng location.latitude và location.longitude
                                    val latLng = LatLng(location.latitude, location.longitude)
                                    mMap!!.moveCamera(
                                        CameraUpdateFactory.newLatLngZoom(
                                            latLng,
                                            15f
                                        )
                                    )
                                }
                            }
                    }
                    val latLng = LatLng(latitude.toDouble(),longitude.toDouble())
//                    val markerOptions = MarkerOptions().position(latLng).title("Current Location")
//                    mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
//                    mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,7f))
//                    mMap!!.addMarker(markerOptions)
                }
            }

        })
        mLocationHelper?.onCreate()
    }

    override fun onResume() {
        super.onResume()
        @Suppress("DEPRECATION")
        mSensorManager?.registerListener(
            this,
            mSensorManager?.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        val degree = Math.round(p0?.values?.get(0)!!)
        val rotateAnnotation = RotateAnimation(
            currentDegrree,
            (-degree).toFloat(),
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotateAnnotation.duration = 210
        rotateAnnotation.fillAfter = true

        binding.compassInMapFragment.startAnimation(rotateAnnotation)
        currentDegrree = (-degree).toFloat()
    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
    override fun onMapReady(googleMap: GoogleMap) {

    }
}