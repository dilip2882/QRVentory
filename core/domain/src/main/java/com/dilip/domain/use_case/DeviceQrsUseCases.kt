package com.dilip.domain.use_case

import com.dilip.domain.models.device.DeviceQrs
import com.dilip.domain.repository.device.DeviceQrsRepository
import kotlinx.coroutines.flow.Flow

data class DeviceQrsUseCases(
    val addDeviceQr: AddDeviceQr,
    val updateDeviceQr: UpdateDeviceQr,
    val deleteDeviceQr: DeleteDeviceQr,
    val getAllDeviceQrs: GetAllDeviceQrs
)

class AddDeviceQr(private val repository: DeviceQrsRepository) {
    suspend operator fun invoke(deviceQr: DeviceQrs) {
        repository.insertDeviceQr(deviceQr)
    }
}

class UpdateDeviceQr(private val repository: DeviceQrsRepository) {
    suspend operator fun invoke(deviceQr: DeviceQrs) {
        repository.updateDeviceQr(deviceQr)
    }
}

class DeleteDeviceQr(private val repository: DeviceQrsRepository) {
    suspend operator fun invoke(deviceQr: DeviceQrs) {
        repository.deleteDeviceQr(deviceQr)
    }
}

class GetAllDeviceQrs(private val repository: DeviceQrsRepository) {
    operator fun invoke(): Flow<List<DeviceQrs>> {
        return repository.getAllDeviceQrs()
    }
}
