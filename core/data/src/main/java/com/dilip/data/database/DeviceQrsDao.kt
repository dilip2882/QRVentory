package com.dilip.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dilip.domain.models.device.DeviceQrs
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceQrsDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeviceQrCode(deviceQrs: DeviceQrs)

    @Update
    suspend fun updateDeviceQrCode(deviceQrs: DeviceQrs)

    @Delete
    suspend fun deleteDeviceQrCode(deviceQrs: DeviceQrs)

    @Query("SELECT * FROM device_qrs")
    fun getAllDeviceQrCodes(): Flow<List<DeviceQrs>>
}
