package com.dilip.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dilip.domain.models.device.DeviceAssignee
import com.dilip.domain.models.device.DeviceLocation
import com.dilip.domain.models.device.DeviceType
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {

    // DeviceAssignee methods
    @Query("SELECT * FROM DeviceAssignee WHERE deviceId = :deviceId")
    fun getAllAssignees(deviceId: Long): List<DeviceAssignee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignee(assignee: DeviceAssignee)

    @Delete
    suspend fun deleteAssignee(assignee: DeviceAssignee)

    // DeviceLocation methods
    @Query("SELECT * FROM DeviceLocation WHERE deviceId = :deviceId")
    fun getAllLocations(deviceId: Long): List<DeviceLocation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: DeviceLocation)

    @Delete
    suspend fun deleteLocation(location: DeviceLocation)

    // DeviceType methods
    @Query("SELECT * FROM DeviceType WHERE deviceId = :deviceId")
    fun getAllTypes(deviceId: Long): Flow<List<DeviceType>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(type: DeviceType): Long

    @Delete
    suspend fun deleteType(type: DeviceType)
}
