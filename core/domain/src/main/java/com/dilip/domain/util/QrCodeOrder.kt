package com.dilip.domain.util

sealed class QrCodeOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): QrCodeOrder(orderType)
    class Date(orderType: OrderType): QrCodeOrder(orderType)
    class Color(orderType: OrderType): QrCodeOrder(orderType)

    fun copy(orderType: OrderType): QrCodeOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
