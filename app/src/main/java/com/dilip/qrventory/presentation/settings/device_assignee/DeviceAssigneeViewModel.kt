package com.dilip.qrventory.presentation.settings.device_assignee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.domain.models.device.DeviceAssignee
import com.dilip.domain.use_case.DeviceAssigneeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceAssigneeViewModel @Inject constructor(
    private val deviceAssigneeUseCase: DeviceAssigneeUseCases,
) : ViewModel() {

    private val _assignees = MutableStateFlow<List<DeviceAssignee>>(emptyList())
    val assignees = _assignees.asStateFlow()

    init {
        loadAssignees()
    }

    private fun loadAssignees() {
        viewModelScope.launch(Dispatchers.IO) {
            _assignees.value = deviceAssigneeUseCase.getDeviceAssigneesUseCase(1)
        }
    }

    fun addAssignee(name: String) {
        viewModelScope.launch {
            val newAssignee = DeviceAssignee(name = name, deviceId = 1)
            deviceAssigneeUseCase.addDeviceAssigneeUseCase(newAssignee)
            loadAssignees()
        }
    }

    fun deleteAssignee(assignee: DeviceAssignee) {
        viewModelScope.launch {
            deviceAssigneeUseCase.deleteDeviceAssigneeUseCase(assignee)
            loadAssignees()
        }
    }
}
