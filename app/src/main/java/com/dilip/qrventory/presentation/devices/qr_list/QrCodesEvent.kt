package com.dilip.qrventory.presentation.devices.qr_list

import com.dilip.domain.models.QrCode
import com.dilip.domain.util.QrCodeOrder

sealed class QrCodesEvent {
    data class Order(val qrCodeOrder: QrCodeOrder): QrCodesEvent()
    data class DeleteQrCode(val qrCode: QrCode): QrCodesEvent()
    object RestoreQrCode: QrCodesEvent()
    object ToggleOrderSection: QrCodesEvent()

}