package com.example.mymap.view.view.map

import android.location.Address


class LocationData {
    var addressLine: String? = null
    var longitude = 0f
    var latitude = 0f
    var temp = 0f
    var humidity = 0f
    var pressure = 0f
    var tempMax = 0f
    var tempMin = 0f
//    private var sunshine: Sunshine? = null
    var id = 0
    var altitude = 0.0
    var address: Address? = null

    constructor() {}
    constructor(temp: Float, humidity: Float, pressure: Float, tempMax: Float, tempMin: Float) {
        this.temp = temp
        this.humidity = humidity
        this.pressure = pressure
        this.tempMax = tempMax
        this.tempMin = tempMin
    }

    override fun toString(): String {
        return "WeatherData{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", tempMax=" + tempMax +
                ", tempMin=" + tempMin +
//                ", sunshine=" + sunshine +
                ", id=" + id +
                '}'
    }

//    fun getSunshine(): Sunshine? {
//        return sunshine
//    }
//
//    fun setSunshine(sunshine: Sunshine?) {
//        this.sunshine = sunshine
//    }
}