package com.dilip.domain.use_case

import com.dilip.domain.models.device.Device
import com.dilip.domain.repository.device.DeviceRepository
import com.dilip.domain.repository.device.DeviceWithDetails
import kotlinx.coroutines.flow.Flow

data class DeviceUseCases(
    val getDevicesWithDetails: GetDevicesWithDetails,
    val addDevice: AddDevice,
    val deleteDevice: DeleteDevice
)

class GetDevicesWithDetails(
    private val repository: DeviceRepository
) {
    operator fun invoke(): Flow<List<DeviceWithDetails>> {
        return repository.getDevicesWithDetails()
    }
}

class AddDevice(
    private val repository: DeviceRepository
) {
    suspend operator fun invoke(deviceWithDetails: DeviceWithDetails) {
        repository.addDevice(deviceWithDetails)
    }
}

class DeleteDevice(
    private val repository: DeviceRepository
) {
    suspend operator fun invoke(device: Device) {
        repository.deleteDevice(device)
    }
}
