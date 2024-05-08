@file:Suppress("DEPRECATION")

package org.compose_projects.find_devices_on_network.core

import android.content.Context
import android.net.wifi.WifiManager
import org.compose_projects.find_devices_on_network.core.get_devices_wifi.GetDevicesWifi
import org.compose_projects.find_devices_on_network.core_get_devices_hotspot.GetDevicesHostPot

class GetDevices {

    fun getPrefixDevices(context: Context): String = getWifiIpAddress(context)

    fun getDevicesWifi(context: Context): List<String> =
        GetDevicesWifi().scanWifi(
            context = context,
            prefix = "192.168.100."
        )

    fun getDevicesHotsPot(context: Context): List<String> =
        GetDevicesHostPot().scanHotsPot(
            context = context,
            prefix = getPrefixDevices(context)
        )


}


private fun getWifiIpAddress(context: Context): String {
    val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val ipAddress = wifiManager.connectionInfo.ipAddress
    return if (ipAddress != 0) {
        String.format(
            "%d.%d.%d.",
            (ipAddress and 0xff),
            (ipAddress shr 8 and 0xff),
            (ipAddress shr 16 and 0xff),
            (ipAddress shr 24 and 0xff)
        )
    } else {
        ""
    }
}

