package com.dilip.data.repository.device

import com.dilip.data.database.DeviceQrsDao
import com.dilip.domain.models.device.DeviceQrs
import com.dilip.domain.repository.device.DeviceQrsRepository
import kotlinx.coroutines.flow.Flow

class DeviceQrsRepositoryImpl(
    private val dao: DeviceQrsDao,
) : DeviceQrsRepository {

    override suspend fun insertDeviceQr(deviceQr: DeviceQrs) {
        dao.insertDeviceQrCode(deviceQr)
    }

    override suspend fun updateDeviceQr(deviceQr: DeviceQrs) {
        dao.updateDeviceQrCode(deviceQr)
    }

    override suspend fun deleteDeviceQr(deviceQr: DeviceQrs) {
        dao.deleteDeviceQrCode(deviceQr)
    }

    override fun getAllDeviceQrs(): Flow<List<DeviceQrs>> {
        return dao.getAllDeviceQrCodes()
    }
}
