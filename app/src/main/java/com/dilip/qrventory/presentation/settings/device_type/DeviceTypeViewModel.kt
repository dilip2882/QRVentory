package com.dilip.qrventory.presentation.settings.device_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.domain.models.device.DeviceType
import com.dilip.domain.use_case.DeviceTypeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceTypeViewModel @Inject constructor(
    private val deviceTypeUseCases: DeviceTypeUseCases,
) : ViewModel() {

    private val _types = MutableStateFlow<List<DeviceType>>(emptyList())
    val types = _types.asStateFlow()

    init {
        loadTypes()
    }

    private fun loadTypes() {
        viewModelScope.launch(Dispatchers.IO) {
            _types.value = deviceTypeUseCases.getDeviceTypesUseCase(1)
        }
    }

    fun addType(typeName: String) {
        viewModelScope.launch {
            val newType = DeviceType(type = typeName, deviceId = 1)
            deviceTypeUseCases.addDeviceTypeUseCase(newType)
            loadTypes()
        }
    }

    fun deleteType(type: DeviceType) {
        viewModelScope.launch {
            deviceTypeUseCases.deleteDeviceTypeUseCase(type)
            loadTypes()
        }
    }
}
