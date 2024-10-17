package com.dilip.domain.use_case

import com.dilip.domain.use_case.device_location.AddDeviceLocationUseCase
import com.dilip.domain.use_case.device_location.DeleteDeviceLocationUseCase
import com.dilip.domain.use_case.device_location.GetDeviceLocationsUseCase

data class DeviceLocationUseCases(
    val getDeviceLocationsUseCase: GetDeviceLocationsUseCase,
    val addDeviceLocationUseCase: AddDeviceLocationUseCase,
    val deleteDeviceLocationUseCase: DeleteDeviceLocationUseCase
)