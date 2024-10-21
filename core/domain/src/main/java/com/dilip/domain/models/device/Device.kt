package com.dilip.domain.models.device

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dilip.common.BabyBlue
import com.dilip.common.LightGreen
import com.dilip.common.Orange
import com.dilip.common.RedPink
import com.dilip.common.Violet

@Entity
data class Device(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val deviceName: String,
    val deviceSN: String,
    val date: String,
    val timestamp: Long,
    val color: Int,
) {
    companion object {
        val qrCodeColors = listOf(Orange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidQrCodeException(message: String) : Exception(message)
