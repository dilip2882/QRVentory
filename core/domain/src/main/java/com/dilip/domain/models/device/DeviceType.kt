package com.dilip.domain.models.device

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeviceType(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val deviceId: Long, // Foreign key to Device
    val type: String,
)
