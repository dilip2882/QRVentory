package com.dilip.domain.use_case.device_assignee

import com.dilip.domain.models.device.DeviceAssignee
import com.dilip.domain.repository.device.DeviceAssigneeRepository

class AddDeviceAssigneeUseCase(
    private val repository: DeviceAssigneeRepository
) {
    suspend operator fun invoke(assignee: DeviceAssignee) {
        repository.insertAssignee(assignee)
    }
}