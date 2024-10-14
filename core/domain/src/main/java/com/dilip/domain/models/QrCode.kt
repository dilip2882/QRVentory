package com.dilip.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dilip.common.*

@Entity
data class QrCode(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null,
    @ColumnInfo(name = "qr_image_url")
    val qrImageUrl: String?
) {
    companion object {
        val qrCodeColors = listOf(Orange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidQrCodeException(message: String): Exception(message)