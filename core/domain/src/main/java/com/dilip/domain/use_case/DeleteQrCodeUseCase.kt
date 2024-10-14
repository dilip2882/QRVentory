package com.dilip.domain.use_case

import com.dilip.domain.models.QrCode
import com.dilip.domain.repository.QRCodeRepository

class DeleteQrCodeUseCase(
    private val repository: QRCodeRepository
) {

    suspend operator fun invoke(qrCode: QrCode) {
        repository.deleteQrCode(qrCode)
    }
}