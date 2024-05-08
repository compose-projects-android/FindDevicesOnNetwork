package org.compose_projects.find_devices_on_network.core.get_devices_wifi

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

@Suppress("DEPRECATION")
class GetDevicesWifi {

    @SuppressLint("MissingPermission")
    fun checkNetworkState(context: Context) : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            // Para versiones antiguas
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }

    }
    fun scanWifi(context: Context, prefix: String): List<String> {
        val ipDevices = mutableListOf<String>()
        val stateNetWork = checkNetworkState(context)

        if (stateNetWork){
            //hacer escaneo de todo

        }else{

            //hacer lo contrario
        }

        (0..90).forEach {
            ipDevices.add(prefix + it)
        }
        return ipDevices
    }

}

