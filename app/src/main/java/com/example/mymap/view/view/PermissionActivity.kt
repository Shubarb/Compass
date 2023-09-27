package com.example.mymap.view.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.mymap.R
import com.example.mymap.databinding.ActivityPermissionBinding

class PermissionActivity : AppCompatActivity() {

    private val LOCATION_PERMISSION_CODE = 1
    private lateinit var locationManager: LocationManager
    private lateinit var binding: ActivityPermissionBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_permission)

        // Kiểm tra trạng thái định vị
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        val isLocationPermissionGranted = (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        binding.switc.isChecked = isLocationPermissionGranted && isLocationEnabled

        binding.switc.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Bật định vị
                requestLocationPermission()
                binding.btnContinue2.isEnabled = true
            } else {
                disableLocation()
                binding.btnContinue2.isEnabled = false
            }
        }

        binding.btnContinue2.setOnClickListener {
            val intent = Intent(this, LanguageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun enableLocation() {
        // Bật định vị bằng cách chuyển người dùng đến cài đặt định vị
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            val settingsIntent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(settingsIntent)
        }
    }

    private fun disableLocation() {
        // Tắt định vị bằng cách chuyển người dùng đến cài đặt định vị
        val settingsIntent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(settingsIntent)
    }

    private fun requestLocationPermission() {
        // Yêu cầu quyền truy cập định vị từ người dùng
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableLocation()
                Toast.makeText(this,"Quyền định vị đã được cấp",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Quyền định vị bị từ chối",Toast.LENGTH_SHORT).show()
            }
        }
    }

}