package com.dilip.data.repository.device

import com.dilip.data.local.database.DeviceDao
import com.dilip.domain.models.device.DeviceAssignee
import com.dilip.domain.repository.device.DeviceAssigneeRepository

class DeviceAssigneeRepositoryImpl(
    private val deviceAssigneeDao: DeviceDao,
) : DeviceAssigneeRepository {
    override suspend fun getAllAssignees(deviceId: Long): List<DeviceAssignee> {
        return deviceAssigneeDao.getAllAssignees(deviceId)
    }

    override suspend fun insertAssignee(assignee: DeviceAssignee) {
        deviceAssigneeDao.insertAssignee(assignee)
    }

    override suspend fun deleteAssignee(assignee: DeviceAssignee) {
        deviceAssigneeDao.deleteAssignee(assignee)
    }
}
