package com.dilip.domain.use_case.device_assignee

import com.dilip.domain.models.device.DeviceAssignee
import com.dilip.domain.repository.device.DeviceAssigneeRepository

class GetDeviceAssigneesUseCase(
    private val repository: DeviceAssigneeRepository
) {
    suspend operator fun invoke(deviceId: Long): List<DeviceAssignee> {
        return repository.getAllAssignees(deviceId)
    }
}
