package com.example.mymap.view.view.splash

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Pair
import androidx.databinding.DataBindingUtil
import com.example.mymap.R
import com.example.mymap.databinding.ActivitySplashBinding
import com.example.mymap.view.view.PermissionActivity

class SplashActivity : AppCompatActivity() {
    private var SPLASH_SCREEN: Long = 2500
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, PermissionActivity::class.java)
            startActivity(intent)
        }, SPLASH_SCREEN)
    }
}