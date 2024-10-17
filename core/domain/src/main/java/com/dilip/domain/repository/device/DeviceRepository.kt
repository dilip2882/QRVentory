package com.dilip.domain.repository.device

import com.dilip.domain.models.device.Device
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {
    fun getDevicesWithDetails(): Flow<List<DeviceWithDetails>>
    suspend fun addDevice(deviceWithDetails: DeviceWithDetails)
    suspend fun deleteDevice(device: Device)
}
