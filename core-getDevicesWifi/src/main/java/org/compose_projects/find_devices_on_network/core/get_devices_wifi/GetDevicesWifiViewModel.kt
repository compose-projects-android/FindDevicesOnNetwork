package org.compose_projects.find_devices_on_network.core.get_devices_wifi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class GetDevicesWifiViewModel (private val networkRepository: NetworkRepository = NetworkRepositoryImp()): ViewModel() {
    private val _chats = MutableStateFlow(listOf<IpDevice>())

    fun getChats(){
        viewModelScope.launch (Dispatchers.IO){
            val devices= networkRepository.getActiveIpDevices()
            _chats.value = devices
        }
    }

}