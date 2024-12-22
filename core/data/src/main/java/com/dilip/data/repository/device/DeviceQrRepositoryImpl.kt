package com.dilip.data.repository.device

import com.dilip.data.local.database.DeviceQrDao
import com.dilip.data.local.mapper.toDomain
import com.dilip.data.local.mapper.toEntity
import com.dilip.data.remote.FirebaseDeviceQrDataSource
import com.dilip.domain.models.device.DeviceQr
import com.dilip.domain.repository.device.DeviceQrRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

class DeviceQrRepositoryImpl(
    private val localDataSource: DeviceQrDao,
    private val remoteDataSource: FirebaseDeviceQrDataSource,
) : DeviceQrRepository {

    override suspend fun insertDeviceQr(deviceQr: DeviceQr): Long {
        val deviceQrEntity = deviceQr.toEntity()
        val insertedId = localDataSource.insertDeviceQrCode(deviceQrEntity)
        remoteDataSource.addDeviceQr(deviceQr)
        return insertedId
    }

    override suspend fun updateDeviceQr(deviceQr: DeviceQr) {
        localDataSource.updateDeviceQrCode(deviceQr.toEntity())
        remoteDataSource.updateDeviceQr(deviceQr)
    }

    override suspend fun deleteDeviceQr(deviceQr: DeviceQr) {
        localDataSource.deleteDeviceQrCode(deviceQr.toEntity())
        remoteDataSource.deleteDeviceQr(deviceQr.id)
    }

    override fun getAllDeviceQrs(): Flow<List<DeviceQr>> = merge(
        localDataSource.getAllDeviceQrCodes().map { list -> list.map { it.toDomain() } },
        remoteDataSource.getAllDeviceQrs(),
    ).distinctUntilChanged()

    override fun getDeviceQrById(id: Int): Flow<DeviceQr?> = merge(
        remoteDataSource.getDeviceQrById(id),
        localDataSource.getDeviceQrById(id).map { it?.toDomain() },
    ).distinctUntilChanged()
}
