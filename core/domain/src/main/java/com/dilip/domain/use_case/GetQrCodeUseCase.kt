package com.dilip.domain.use_case

import com.dilip.domain.models.QrCode
import com.dilip.domain.repository.QRCodeRepository
import com.dilip.domain.util.OrderType
import com.dilip.domain.util.QrCodeOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetQrCodeUseCase(
    private val repository: QRCodeRepository
) {
    operator fun invoke(
        qrCodeOrder: QrCodeOrder = QrCodeOrder.Date(OrderType.Descending)
    ): Flow<List<QrCode>> {
        return repository.getAllQrCodes().map { qrCodes ->
            when (qrCodeOrder.orderType) {
                is OrderType.Ascending -> {
                    when (qrCodeOrder) {
                        is QrCodeOrder.Title -> qrCodes.sortedBy { it.title.lowercase() }
                        is QrCodeOrder.Date -> qrCodes.sortedBy { it.timestamp }
                        is QrCodeOrder.Color -> qrCodes.sortedBy { it.color }
                    }
                }

                is OrderType.Descending -> {
                    when (qrCodeOrder) {
                        is QrCodeOrder.Title -> qrCodes.sortedByDescending { it.title.lowercase() }
                        is QrCodeOrder.Date -> qrCodes.sortedByDescending { it.timestamp }
                        is QrCodeOrder.Color -> qrCodes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}