package com.dilip.domain.repository.device

import com.dilip.domain.models.device.DeviceType

interface DeviceTypeRepository {
    suspend fun getAllTypes(deviceId: Long): List<DeviceType>
    suspend fun insertType(type: DeviceType)
    suspend fun deleteType(type: DeviceType)
}
