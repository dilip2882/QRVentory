package com.dilip.domain.use_case

import com.dilip.domain.use_case.device_assignee.AddDeviceAssigneeUseCase
import com.dilip.domain.use_case.device_assignee.DeleteDeviceAssigneeUseCase
import com.dilip.domain.use_case.device_assignee.GetDeviceAssigneesUseCase

data class DeviceAssigneeUseCases(
    // Device Assignee
    val getDeviceAssigneesUseCase: GetDeviceAssigneesUseCase,
    val addDeviceAssigneeUseCase: AddDeviceAssigneeUseCase,
    val deleteDeviceAssigneeUseCase: DeleteDeviceAssigneeUseCase,
)
