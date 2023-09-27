package com.example.mymap.view.view.main

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.mymap.R
import com.example.mymap.databinding.ActivityHomeBinding
import com.example.mymap.view.view.map.LocationData
import com.example.mymap.view.view.map.LocationHelper
import com.example.mymap.view.view.map.MapActivity
import com.example.mymap.view.view.setting.SettingActivity
import com.example.mymap.view.view.theme.ThemeActivity

class HomeActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var mTypeCompassFragment: TypeCompassFragment
    private lateinit var locationManager: LocationManager

    private var currentDegrree = 0f
    private var mLocationHelper: LocationHelper? = null
    private var latitude = 0.0f
    private var longitude= 0.0f
    private var addressStr: String = ""

    private var mSensorManager: SensorManager? = null

    //dialog ADS
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        //Khởi tạo
        mTypeCompassFragment = TypeCompassFragment()

        mLocationHelper = LocationHelper(this)

        initData()

        eventClickItem()

        getLocation()
    }

    private fun eventClickItem() {
        binding.btnType.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.layoutFragmentType, mTypeCompassFragment)
                addToBackStack(null)
                commit()
            }
        }

        binding.btnADS.setOnClickListener {
            dialogRemoveAds()
        }

        binding.btnTheme.setOnClickListener {
            val intent = Intent(this, ThemeActivity::class.java)
            startActivity(intent)
        }

        binding.btnSetting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        binding.btnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }

    private fun dialogRemoveAds() {
        // Khởi tạo dialog
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.popup)

        // Thiết lập cửa sổ cho dialog
        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        // Thiết lập sự kiện onTouchListener để cho phép di chuyển dialog
        dialog.findViewById<View>(R.id.layoutDialog)
            ?.setOnTouchListener(object : View.OnTouchListener {
                private var dx = 0f
                private var dy = 0f

                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> {
                            dx = event.rawX - dialog.window?.attributes?.x!!
                            dy = event.rawY - dialog.window?.attributes?.y!!
                        }
                        MotionEvent.ACTION_MOVE -> {
                            val layoutParams = dialog.window?.attributes
                            layoutParams?.x = (event.rawX - dx).toInt()
                            layoutParams?.y = (event.rawY - dy).toInt()
                            dialog.window?.attributes = layoutParams
                        }
                    }
                    return true
                }
            })


        val btnLater = dialog.findViewById<TextView>(R.id.btnLater)
        btnLater.setOnClickListener {
            dialog.dismiss()
        }

        val btnPay = dialog.findViewById<RelativeLayout>(R.id.btnPay)
        btnPay.setOnClickListener {
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
        }

        // Hiển thị dialog
        dialog.show()
    }

    //Compass
    private fun initData() {
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
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

        binding.compassMain.startAnimation(rotateAnnotation)
        currentDegrree = (-degree).toFloat()

        if (degree <= 45 || degree > 315) {
            binding.degree.setText("$degree°N")
        } else if (degree in 45..135) {
            binding.degree.setText("$degree°E")
        } else if (degree in 135..225) {
            binding.degree.setText("$degree°S")
        } else if (degree in 225..315) {
            binding.degree.setText("$degree°W")
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    //Location
    private fun getLocation() {
        mLocationHelper?.setLocationValueListener(object : LocationHelper.LocationDataChangeListener {
            override fun onUpdateLocationData(locationData: LocationData?) {
                if (locationData != null) {
                    //address
                    addressStr = locationData.addressLine ?: ""
                    //location
                    latitude = locationData.latitude
                    longitude = locationData.longitude
                    binding.locationHere.text = "$latitude $longitude"
                    Toast.makeText(applicationContext,"$latitude  $longitude",Toast.LENGTH_SHORT).show()
//                    loadChooseMap()
                }
            }
        })
        mLocationHelper?.onCreate()
    }

}