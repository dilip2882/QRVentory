package com.dilip.qrventory.presentation.settings.device_location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.domain.models.device.DeviceLocation
import com.dilip.domain.use_case.DeviceLocationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceLocationViewModel @Inject constructor(
    private val deviceLocationUseCase: DeviceLocationUseCases
) : ViewModel() {

    private val _locations = MutableStateFlow<List<DeviceLocation>>(emptyList())
    val locations = _locations.asStateFlow()

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            _locations.value = deviceLocationUseCase.getDeviceLocationsUseCase(1)
        }
    }

    fun addLocation(location: String) {
        viewModelScope.launch {
            val newLocation = DeviceLocation(location = location, deviceId = 1)
            deviceLocationUseCase.addDeviceLocationUseCase(newLocation)
            loadLocations()
        }
    }

    fun deleteLocation(location: DeviceLocation) {
        viewModelScope.launch {
            deviceLocationUseCase.deleteDeviceLocationUseCase(location)
            loadLocations()
        }
    }
}
