package com.dilip.domain.models.device

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeviceLocation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val deviceId: Long,
    val location: String
)
