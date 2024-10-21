package com.dilip.domain.use_case

import com.dilip.domain.use_case.device_type.AddDeviceTypeUseCase
import com.dilip.domain.use_case.device_type.DeleteDeviceTypeUseCase
import com.dilip.domain.use_case.device_type.GetDeviceTypesUseCase

data class DeviceTypeUseCases(
    val getDeviceTypesUseCase: GetDeviceTypesUseCase,
    val addDeviceTypeUseCase: AddDeviceTypeUseCase,
    val deleteDeviceTypeUseCase: DeleteDeviceTypeUseCase,
)
