package org.compose_projects.find_devices_on_network.core.get_devices_wifi

data class IpDevice(
    val octet1: Int,
    val octet2: Int,
    val octet3: Int,
    val octet4: Int,
    val isActive: Boolean
){
    override fun toString(): String {
        return "$octet1.$octet2.$octet3.$octet4"
    }
}