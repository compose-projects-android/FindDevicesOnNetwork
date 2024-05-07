package org.compose_projects.find_devices_on_network.core_get_devices_hotspot

import android.content.Context

class GetDevicesHostPot {

    fun checkHotsPotState(): Boolean {
        return  true
    }

    fun scanHotsPot(context: Context, prefix: String): List<String> {
        val ipDevices = mutableListOf<String>()
        (0..20).forEach {
            ipDevices.add(prefix + it)
        }
        return ipDevices
    }

}