package com.dilip.data.remote.mapper

import com.dilip.domain.models.device.DeviceQr
import com.google.firebase.firestore.DocumentSnapshot

fun DeviceQr.toMap(): Map<String, Any?> = mapOf(
    "id" to id,
    "deviceType" to deviceType,
    "deviceSN" to deviceSN,
    "deviceAssignee" to deviceAssignee,
    "date" to date,
    "location" to location,
    "deviceQr" to deviceQr,
)

fun DocumentSnapshot.toDeviceQr(): DeviceQr? {
    return try {
        DeviceQr(
            id = getLong("id")?.toInt() ?: 0,
            deviceType = getString("deviceType") ?: "",
            deviceSN = getString("deviceSN") ?: "",
            deviceAssignee = getString("deviceAssignee") ?: "",
            date = getString("date") ?: "",
            location = getString("location") ?: "",
            deviceQr = getBlob("deviceQr")?.toBytes(),
        )
    } catch (e: Exception) {
        null
    }
}
