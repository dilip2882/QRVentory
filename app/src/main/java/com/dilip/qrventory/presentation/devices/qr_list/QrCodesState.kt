package com.dilip.qrventory.presentation.devices.qr_list

import com.dilip.domain.models.QrCode
import com.dilip.domain.util.OrderType
import com.dilip.domain.util.QrCodeOrder

data class QrCodesState(
    val qrCodes: List<QrCode> = emptyList(),
    val qrCodeOrder: QrCodeOrder = QrCodeOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,

)