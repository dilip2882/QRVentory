package com.dilip.domain.repository

import com.dilip.domain.models.QrCode
import kotlinx.coroutines.flow.Flow

interface QRCodeRepository {

    fun getAllQrCodes(): Flow<List<QrCode>>

    suspend fun getQrCodeById(id: Int): QrCode?

    suspend fun insertQrCode(qrCode: QrCode)

    suspend fun deleteQrCode(qrCode: QrCode)
}

