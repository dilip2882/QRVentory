package com.dilip.domain.repository.device

import com.dilip.domain.models.device.DeviceQrs
import kotlinx.coroutines.flow.Flow

interface DeviceQrsRepository {
    suspend fun insertDeviceQr(deviceQr: DeviceQrs)
    suspend fun updateDeviceQr(deviceQr: DeviceQrs)
    suspend fun deleteDeviceQr(deviceQr: DeviceQrs)
    fun getAllDeviceQrs(): Flow<List<DeviceQrs>>
}
