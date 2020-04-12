package com.rk.cleanarchitecturekotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object NetworkUtility {
    private var newtworkAvailabilityMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()


    fun getNetworkAvailability(context: Context): LiveData<Boolean> {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val callback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                newtworkAvailabilityMutableLiveData.postValue(true)
            }

            override fun onLost(network: Network) {
                newtworkAvailabilityMutableLiveData.postValue(false)

            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(callback)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), callback)
        }

        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetworkInfo.isConnected
        newtworkAvailabilityMutableLiveData.postValue(isConnected)
        return newtworkAvailabilityMutableLiveData
    }
}