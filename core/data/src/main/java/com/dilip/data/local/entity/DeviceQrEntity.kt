package com.dilip.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "device_qrs")
data class DeviceQrEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val deviceType: String,
    val deviceSN: String,
    val deviceAssignee: String,
    val date: String,
    val location: String,
    val deviceQr: ByteArray? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DeviceQrEntity

        if (id != other.id) return false
        if (deviceType != other.deviceType) return false
        if (deviceSN != other.deviceSN) return false
        if (deviceAssignee != other.deviceAssignee) return false
        if (date != other.date) return false
        if (location != other.location) return false
        if (deviceQr != null) {
            if (other.deviceQr == null) return false
            if (!deviceQr.contentEquals(other.deviceQr)) return false
        } else if (other.deviceQr != null) {
            return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + deviceType.hashCode()
        result = 31 * result + deviceSN.hashCode()
        result = 31 * result + deviceAssignee.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + (deviceQr?.contentHashCode() ?: 0)
        return result
    }
}
