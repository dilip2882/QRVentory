package com.dilip.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
