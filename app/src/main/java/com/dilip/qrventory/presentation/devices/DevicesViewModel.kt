package com.dilip.qrventory.presentation.devices

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dilip.domain.models.device.DeviceQrs
import com.dilip.domain.use_case.DeviceQrsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevicesViewModel @Inject constructor(
    private val deviceQrsUseCases: DeviceQrsUseCases
) : ViewModel() {

    val devicesQr: StateFlow<List<DeviceQrs>> = deviceQrsUseCases.getAllDeviceQrs().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addDeviceQr(deviceQr: DeviceQrs) {
        viewModelScope.launch(Dispatchers.IO) {
            deviceQrsUseCases.addDeviceQr(deviceQr)
        }
    }

    fun updateDeviceQr(deviceQr: DeviceQrs) = viewModelScope.launch {
        deviceQrsUseCases.updateDeviceQr(deviceQr)
    }

    fun deleteDeviceQr(deviceQr: DeviceQrs) = viewModelScope.launch {
        deviceQrsUseCases.deleteDeviceQr(deviceQr)
    }
}

