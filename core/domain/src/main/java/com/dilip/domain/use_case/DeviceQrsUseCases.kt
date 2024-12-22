package com.dilip.domain.use_case

import com.dilip.domain.models.device.DeviceQr
import com.dilip.domain.repository.device.DeviceQrRepository
import kotlinx.coroutines.flow.Flow

data class DeviceQrsUseCases(
    val addDeviceQr: AddDeviceQr,
    val updateDeviceQr: UpdateDeviceQr,
    val deleteDeviceQr: DeleteDeviceQr,
    val getAllDeviceQrs: GetAllDeviceQrs,
)

class AddDeviceQr(private val repository: DeviceQrRepository) {
    suspend operator fun invoke(deviceQr: DeviceQr) {
        repository.insertDeviceQr(deviceQr)
    }
}

class UpdateDeviceQr(private val repository: DeviceQrRepository) {
    suspend operator fun invoke(deviceQr: DeviceQr) {
        repository.updateDeviceQr(deviceQr)
    }
}

class DeleteDeviceQr(private val repository: DeviceQrRepository) {
    suspend operator fun invoke(deviceQr: DeviceQr) {
        repository.deleteDeviceQr(deviceQr)
    }
}

class GetAllDeviceQrs(private val repository: DeviceQrRepository) {
    operator fun invoke(): Flow<List<DeviceQr>> {
        return repository.getAllDeviceQrs()
    }
}
