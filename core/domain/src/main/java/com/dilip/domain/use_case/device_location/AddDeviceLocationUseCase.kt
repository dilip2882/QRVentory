package com.dilip.domain.use_case.device_location

import com.dilip.domain.models.device.DeviceLocation
import com.dilip.domain.repository.device.DeviceLocationRepository

class AddDeviceLocationUseCase(
    private val repository: DeviceLocationRepository
) {
    suspend operator fun invoke(location: DeviceLocation) {
        repository.insertLocation(location)
    }
}