package com.dilip.domain.models.device

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeviceAssignee(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val deviceId: Long,
    val name: String
)