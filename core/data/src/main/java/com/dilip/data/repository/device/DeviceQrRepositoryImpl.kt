package com.dilip.data.repository.device

import com.dilip.data.database.DeviceQrDao
import com.dilip.data.mapper.toDomain
import com.dilip.data.mapper.toEntity
import com.dilip.domain.models.device.DeviceQr
import com.dilip.domain.repository.device.DeviceQrRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DeviceQrRepositoryImpl(
    private val dao: DeviceQrDao,
) : DeviceQrRepository {

    override suspend fun insertDeviceQr(deviceQr: DeviceQr): Long {
        val deviceQrEntity = deviceQr.toEntity()
        dao.insertDeviceQrCode(deviceQrEntity)
        return deviceQrEntity.id.toLong()
    }

    override suspend fun updateDeviceQr(deviceQr: DeviceQr) {
        dao.updateDeviceQrCode(deviceQr.toEntity())
    }

    override suspend fun deleteDeviceQr(deviceQr: DeviceQr) {
        dao.deleteDeviceQrCode(deviceQr.toEntity())
    }

    override fun getAllDeviceQrs(): Flow<List<DeviceQr>> {
        return dao.getAllDeviceQrCodes().map { list -> list.map { it.toDomain() } }
    }

    override fun getDeviceQrById(id: Int): Flow<DeviceQr?> {
        return dao.getDeviceQrById(id).map { it?.toDomain() }
    }
}
