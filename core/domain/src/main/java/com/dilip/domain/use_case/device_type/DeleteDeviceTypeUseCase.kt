package com.dilip.domain.use_case.device_type

import com.dilip.domain.models.device.DeviceType
import com.dilip.domain.repository.device.DeviceTypeRepository

class DeleteDeviceTypeUseCase(
    private val repository: DeviceTypeRepository,
) {
    suspend operator fun invoke(type: DeviceType) {
        repository.deleteType(type)
    }
}
