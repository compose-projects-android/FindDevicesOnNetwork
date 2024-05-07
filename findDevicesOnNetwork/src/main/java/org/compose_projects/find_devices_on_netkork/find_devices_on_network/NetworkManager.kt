package org.compose_projects.find_devices_on_netkork.find_devices_on_network

import android.content.Context
import org.compose_projects.find_devices_on_network.core.GetDevices

class NetworkManager(private val context: Context) {
    fun devicesWifi() = GetDevices().getDevicesWifi(context)
    fun devicesHotsPot() = GetDevices().getDevicesHotsPot(context)

}