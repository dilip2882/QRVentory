package com.dilip.domain.repository.device

import androidx.room.Embedded
import androidx.room.Relation
import com.dilip.domain.models.device.Device
import com.dilip.domain.models.device.DeviceAssignee
import com.dilip.domain.models.device.DeviceLocation
import com.dilip.domain.models.device.DeviceType

data class DeviceWithDetails(
    @Embedded val device: Device,
    @Relation(
        parentColumn = "id",
        entityColumn = "deviceId"
    )
    val assignees: List<DeviceAssignee>,
    @Relation(
        parentColumn = "id",
        entityColumn = "deviceId"
    )
    val locations: List<DeviceLocation>,
    @Relation(
        parentColumn = "id",
        entityColumn = "deviceId"
    )
    val types: List<DeviceType>
)
