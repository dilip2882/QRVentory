package com.dilip.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dilip.data.local.entity.DeviceQrEntity

@Database(entities = [DeviceQrEntity::class], version = 1)
abstract class DeviceQrsDatabase : RoomDatabase() {
    abstract fun deviceQrsDao(): DeviceQrDao
}
