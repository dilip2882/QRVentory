package com.dilip.qrventory.presentation.devices.device_list

import com.dilip.domain.models.device.Device

data class QrCodesState(
    val devices: List<Device> = emptyList(),

    )