package com.dilip.data.repository.device

import com.dilip.data.local.database.DeviceDao
import com.dilip.domain.models.device.DeviceType
import com.dilip.domain.repository.device.DeviceTypeRepository
import kotlinx.coroutines.flow.first

class DeviceTypeRepositoryImpl(
    private val deviceDao: DeviceDao,
) : DeviceTypeRepository {
    override suspend fun getAllTypes(deviceId: Long): List<DeviceType> {
        return deviceDao.getAllTypes(deviceId).first()
    }

    override suspend fun insertType(type: DeviceType) {
        deviceDao.insertType(type)
    }

    override suspend fun deleteType(type: DeviceType) {
        deviceDao.deleteType(type)
    }
}
