package org.compose_projects.find_devices_on_network.core

import org.compose_projects.find_devices_on_network.core.get_devices_wifi.GetDevicesWifi
import org.compose_projects.find_devices_on_network.core_get_devices_hotspot.GetDevicesHostPot

class GetDevices {
    fun getAllDeviceWifi(): List<Int> {
        val listAllDevices = GetDevicesHostPot().getDevicesHotsPot() + GetDevicesWifi().getDeviceWifi()
        return listAllDevices
    }
}