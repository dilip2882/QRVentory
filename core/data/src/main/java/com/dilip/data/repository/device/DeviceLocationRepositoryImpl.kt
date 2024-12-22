package com.dilip.data.repository.device

import com.dilip.data.local.database.DeviceDao
import com.dilip.domain.models.device.DeviceLocation
import com.dilip.domain.repository.device.DeviceLocationRepository

class DeviceLocationRepositoryImpl(
    private val deviceDao: DeviceDao,
) : DeviceLocationRepository {
    override suspend fun getAllLocations(deviceId: Long): List<DeviceLocation> {
        return deviceDao.getAllLocations(deviceId)
    }

    override suspend fun insertLocation(location: DeviceLocation) {
        deviceDao.insertLocation(location)
    }

    override suspend fun deleteLocation(location: DeviceLocation) {
        deviceDao.deleteLocation(location)
    }
}
