package com.dilip.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dilip.data.entity.DeviceQrEntity

@Database(entities = [DeviceQrEntity::class], version = 1)
abstract class DeviceQrDatabase : RoomDatabase() {
    abstract fun deviceQrDao(): DeviceQrDao
}
