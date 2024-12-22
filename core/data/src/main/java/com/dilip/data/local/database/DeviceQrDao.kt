package com.dilip.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dilip.data.local.entity.DeviceQrEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceQrDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeviceQrCode(deviceQr: DeviceQrEntity): Long

    @Update
    suspend fun updateDeviceQrCode(deviceQr: DeviceQrEntity)

    @Delete
    suspend fun deleteDeviceQrCode(deviceQr: DeviceQrEntity)

    @Query("SELECT * FROM device_qrs")
    fun getAllDeviceQrCodes(): Flow<List<DeviceQrEntity>>

    @Query("SELECT * FROM device_qrs WHERE id = :id")
    fun getDeviceQrById(id: Int): Flow<DeviceQrEntity?>
}
