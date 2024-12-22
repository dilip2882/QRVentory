package com.dilip.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dilip.domain.models.device.Device
import com.dilip.domain.models.device.DeviceAssignee
import com.dilip.domain.models.device.DeviceLocation
import com.dilip.domain.models.device.DeviceType

@Database(
    entities = [
        Device::class,
        DeviceType::class,
        DeviceAssignee::class,
        DeviceLocation::class,
    ],
    version = 1,
)
abstract class DeviceDatabase : RoomDatabase() {

    abstract val deviceDao: DeviceDao

    companion object {
        const val DATABASE_NAME = "devices_db"
    }
}
