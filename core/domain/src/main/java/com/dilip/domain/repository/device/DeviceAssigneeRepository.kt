package com.dilip.domain.repository.device

import com.dilip.domain.models.device.DeviceAssignee

interface DeviceAssigneeRepository {
    suspend fun getAllAssignees(deviceId: Long): List<DeviceAssignee>

    suspend fun insertAssignee(assignee: DeviceAssignee)
    suspend fun deleteAssignee(assignee: DeviceAssignee)
}
