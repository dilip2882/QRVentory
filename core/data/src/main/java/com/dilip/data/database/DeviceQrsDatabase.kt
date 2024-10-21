package com.dilip.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dilip.domain.models.device.DeviceQrs

@Database(entities = [DeviceQrs::class], version = 1)
abstract class DeviceQrsDatabase : RoomDatabase() {
    abstract fun deviceQrsDao(): DeviceQrsDao
}
