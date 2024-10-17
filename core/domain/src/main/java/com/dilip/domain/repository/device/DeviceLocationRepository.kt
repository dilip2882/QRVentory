package com.dilip.domain.repository.device

import com.dilip.domain.models.device.DeviceLocation

interface DeviceLocationRepository {
    suspend fun getAllLocations(deviceId: Long): List<DeviceLocation>

    suspend fun insertLocation(location: DeviceLocation)
    suspend fun deleteLocation(location: DeviceLocation)
}
