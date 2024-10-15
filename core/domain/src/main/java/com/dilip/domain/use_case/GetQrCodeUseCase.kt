package com.dilip.domain.use_case

import com.dilip.domain.models.QrCode
import com.dilip.domain.repository.QRCodeRepository

class GetQrCodeUseCase(
    private val repository: QRCodeRepository
) {
    suspend operator fun invoke(id: Int): QrCode? {
        return repository.getQrCodeById(id)
    }
}