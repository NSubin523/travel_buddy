package com.example.travel_scheduler.location_services

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationClient(private val context: Context) {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var deviceLocation: String

    @JvmName("getDeviceLocation1")
    fun getDeviceLocation(): String {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return deviceLocation
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener {
                deviceLocation = it.toString()
            }
        return deviceLocation
    }
}