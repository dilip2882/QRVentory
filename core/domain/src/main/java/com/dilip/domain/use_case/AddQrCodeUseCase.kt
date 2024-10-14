package com.dilip.domain.use_case

import com.dilip.domain.models.InvalidQrCodeException
import com.dilip.domain.models.QrCode
import com.dilip.domain.repository.QRCodeRepository

class AddQrCodeUseCase(
    private val repository: QRCodeRepository
) {

    @Throws(InvalidQrCodeException::class)
    suspend operator fun invoke(qrCode: QrCode) {
        if (qrCode.title.isBlank()) {
            throw InvalidQrCodeException("The title can't be empty.")
        }
        repository.insertQrCode(qrCode)
    }
}