package com.dilip.domain.use_case.device_location

import com.dilip.domain.models.device.DeviceLocation
import com.dilip.domain.repository.device.DeviceLocationRepository

class GetDeviceLocationsUseCase(
    private val repository: DeviceLocationRepository,
) {
    suspend operator fun invoke(deviceId: Long): List<DeviceLocation> {
        return repository.getAllLocations(deviceId)
    }
}
