package org.compose_projects.find_devices_on_network.core

import android.content.Context
import org.compose_projects.find_devices_on_network.core.get_devices_wifi.GetDevicesWifi
import org.compose_projects.find_devices_on_network.core_get_devices_hotspot.GetDevicesHostPot

class GetDevices {

    fun getDevicesWifi(context: Context): List<String> =
        GetDevicesWifi().getDeviceWifi(
            context = context,
            prefix = "192.168.100."
        )


}