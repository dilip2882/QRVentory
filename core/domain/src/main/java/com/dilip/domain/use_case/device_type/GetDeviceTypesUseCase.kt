package com.dilip.domain.use_case.device_type

import com.dilip.domain.models.device.DeviceType
import com.dilip.domain.repository.device.DeviceTypeRepository

class GetDeviceTypesUseCase(
    private val repository: DeviceTypeRepository
) {
    suspend operator fun invoke(deviceId: Long): List<DeviceType> {
        return repository.getAllTypes(deviceId)
    }
}