package com.dilip.data.repository.device

import com.dilip.data.database.DeviceDao
import com.dilip.domain.models.device.Device
import com.dilip.domain.repository.device.DeviceRepository
import com.dilip.domain.repository.device.DeviceWithDetails
import kotlinx.coroutines.flow.Flow

class DeviceRepositoryImpl(
    private val deviceDao: DeviceDao
) : DeviceRepository {

    override fun getDevicesWithDetails(): Flow<List<DeviceWithDetails>> {
        return deviceDao.getAllDevicesWithDetails()
    }

    override suspend fun addDevice(deviceWithDetails: DeviceWithDetails) {
        val deviceId = deviceDao.insertDevice(deviceWithDetails.device)
        deviceWithDetails.types.forEach {
            deviceDao.insertType(it.copy(deviceId = deviceId))
        }
        deviceWithDetails.locations.forEach {
            deviceDao.insertLocation(it.copy(deviceId = deviceId))
        }
        deviceWithDetails.assignees.forEach {
            deviceDao.insertAssignee(it.copy(deviceId = deviceId))
        }
    }

    override suspend fun deleteDevice(device: Device) {
        deviceDao.deleteDevice(device)
    }
}
