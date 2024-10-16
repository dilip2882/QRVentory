package com.dilip.domain.models

import androidx.room.Entity

@Entity
data class Device(
    val deviceType: List<DeviceType>,
    val deviceSN: String,
    val deviceAssignee: List<DeviceAssignee>,
    val location: List<DeviceLocation>,
    val date: String
)

