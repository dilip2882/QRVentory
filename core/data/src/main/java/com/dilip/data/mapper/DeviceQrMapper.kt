package com.dilip.data.mapper

import com.dilip.data.entity.DeviceQrEntity
import com.dilip.domain.models.device.DeviceQr

fun DeviceQrEntity.toDomain(): DeviceQr {
    return DeviceQr(
        id = this.id,
        deviceType = this.deviceType,
        deviceSN = this.deviceSN,
        deviceAssignee = this.deviceAssignee,
        date = this.date,
        location = this.location,
        deviceQr = this.deviceQr,
    )
}

fun DeviceQr.toEntity(): DeviceQrEntity {
    return DeviceQrEntity(
        id = this.id,
        deviceType = this.deviceType,
        deviceSN = this.deviceSN,
        deviceAssignee = this.deviceAssignee,
        date = this.date,
        location = this.location,
        deviceQr = this.deviceQr,
    )
}
