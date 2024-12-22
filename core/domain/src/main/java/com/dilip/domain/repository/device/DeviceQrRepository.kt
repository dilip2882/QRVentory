package com.dilip.domain.repository.device

import com.dilip.domain.models.device.DeviceQr
import kotlinx.coroutines.flow.Flow

interface DeviceQrRepository {
    suspend fun insertDeviceQr(deviceQr: DeviceQr): Long
    suspend fun updateDeviceQr(deviceQr: DeviceQr)
    suspend fun deleteDeviceQr(deviceQr: DeviceQr)
    fun getAllDeviceQrs(): Flow<List<DeviceQr>>
    fun getDeviceQrById(id: Int): Flow<DeviceQr?>
}
