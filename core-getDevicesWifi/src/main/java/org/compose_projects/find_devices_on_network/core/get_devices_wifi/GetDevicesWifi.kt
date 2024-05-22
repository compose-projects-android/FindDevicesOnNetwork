package org.compose_projects.find_devices_on_network.core.get_devices_wifi

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.util.concurrent.Executors

@Suppress("DEPRECATION")
class GetDevicesWifi {

    @SuppressLint("MissingPermission")
    private fun checkNetworkState(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

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

        if (stateNetWork) {
            //hacer escaneo de todo
            getIpDevicesWithPing(prefix){
                ipDevices.add(it)
            }

        } else {
            ipDevices.add("No se encuentra una red para escanear, verifique su conexión a internet")
            //hacer lo contrario
        }

        return if (ipDevices.isEmpty()) {
            ipDevices.add("No se encontraron dispositivos en la red")
            ipDevices
        }else{
            ipDevices
        }

    }

    private fun getIpDevicesWithPing(prefix: String, ipActive: (String) -> Unit) {
        val activeIps = mutableListOf<String>()

        // Crear un pool de hilos con un número fijo de hilos
        val executor =
            Executors.newFixedThreadPool(100) // Puedes ajustar este número según la capacidad de tu red y el rendimiento de tu sistema

        // Lista para almacenar todos los futures de los hilos
        val futures = mutableListOf<java.util.concurrent.Future<*>>()

        // Lanzar pings en paralelo utilizando un pool de hilos
        for (i in 2..255) {
            val ip = "$prefix$i"
            val future = executor.submit {
                try {
                    val process = Runtime.getRuntime().exec("ping -c 1 $ip")
                    val exitVal = process.waitFor()

                    // Si el proceso termina correctamente, la IP responde al ping
                    if (exitVal == 0) {
                        synchronized(activeIps) {
                            activeIps.add(ip)
                            ipActive(ip)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            futures.add(future)
        }

        // Esperar a que todos los hilos terminen
        futures.forEach { it.get() }

        // Apagar el pool de hilos después de su uso
        executor.shutdown()

    }
}


