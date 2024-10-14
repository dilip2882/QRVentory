package com.dilip.data.repository

import com.dilip.data.database.QrDao
import com.dilip.domain.models.QrCode
import com.dilip.domain.repository.QRCodeRepository
import kotlinx.coroutines.flow.Flow

class QRCodeRepositoryImpl(
    private val dao: QrDao
) : QRCodeRepository {
    override fun getAllQrCodes(): Flow<List<QrCode>> {
        return  dao.getAllQrCodesFlow()
    }

    override suspend fun getQrCodeById(id: Int): QrCode? {
        return dao.getQrCodeById(id)
    }

    override suspend fun insertQrCode(qrCode: QrCode) {
        dao.insertQrCode(qrCode)
    }

    override suspend fun deleteQrCode(qrCode: QrCode) {
        dao.deleteQrCode(qrCode)
    }

}

