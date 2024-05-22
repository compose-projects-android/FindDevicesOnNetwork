package org.compose_projects.find_devices_on_netkork.find_devices_on_network.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WifiP2pConnection : Service() {

    private val TAG = "prueba1"
    private var count = 0
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Empezó el servicio")

        scope.launch {
            while (true) {
                delay(1000)
                count++
                Log.d(TAG, "Contador: $count")
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG, "Terminó el servicio")
        scope.cancel()
        super.onDestroy()
    }

}