package com.dilip.qrventory.presentation.devices.device_list.components

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dilip.domain.models.device.DeviceAssignee
import com.dilip.domain.models.device.DeviceLocation
import com.dilip.domain.models.device.DeviceType
import com.dilip.qrventory.presentation.settings.device_assignee.DeviceAssigneeViewModel
import com.dilip.qrventory.presentation.settings.device_location.DeviceLocationViewModel
import com.dilip.qrventory.presentation.settings.device_type.DeviceTypeViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDevice(
    navController: NavController
) {
    val deviceTypeViewModel: DeviceTypeViewModel = hiltViewModel()
    val deviceAssigneeViewModel: DeviceAssigneeViewModel = hiltViewModel()
    val deviceLocationViewModel: DeviceLocationViewModel = hiltViewModel()

    var expandedAssignee by remember { mutableStateOf(false) }
    var selectedAssignee by remember { mutableStateOf<DeviceAssignee?>(null) }

    var deviceName by remember { mutableStateOf("") }
    var deviceSerialNo by remember { mutableStateOf("") }

    val assignees by deviceAssigneeViewModel.assignees.collectAsState(initial = emptyList())
    var expandedType by remember { mutableStateOf(false) }
    var selectedDeviceType by remember { mutableStateOf<DeviceType?>(null) }
    val deviceTypes by deviceTypeViewModel.types.collectAsState(initial = emptyList())

    var expandedLocation by remember { mutableStateOf(false) }
    var selectedLocation by remember { mutableStateOf<DeviceLocation?>(null) }
    val locations by deviceLocationViewModel.locations.collectAsState(initial = emptyList())

    var datePickerVisible by remember { mutableStateOf(false) }
    var dataState = rememberDatePickerState()
    var selectedDate by remember { mutableStateOf(Date().time) }  // Initialize with current time

    Column {
        TopAppBar(
            title = { Text("Add Device") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
        ) {

            item {
                OutlinedTextField(
                    value = deviceSerialNo,
                    onValueChange = { deviceSerialNo = it },
                    label = { Text("Device Serial Number") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }

            item {
                ExposedDropdownMenuBox(
                    expanded = expandedType,
                    onExpandedChange = { expandedType = !expandedType }
                ) {
                    TextField(
                        readOnly = true,
                        value = selectedDeviceType?.type ?: "Select Device Type",
                        onValueChange = {},
                        label = { Text("Device Type/Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .menuAnchor()
                    )

                    DropdownMenu(
                        expanded = expandedType,
                        onDismissRequest = { expandedType = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        deviceTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type.type) },
                                onClick = {
                                    selectedDeviceType = type
                                    expandedType = false
                                }
                            )
                        }
                    }
                }
            }

            item {
                ExposedDropdownMenuBox(
                    expanded = expandedAssignee,
                    onExpandedChange = { expandedAssignee = !expandedAssignee }
                ) {
                    TextField(
                        readOnly = true,
                        value = selectedAssignee?.name ?: "Select Assignee",
                        onValueChange = {},
                        label = { Text("Device Assignee") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .menuAnchor()
                    )

                    DropdownMenu(
                        expanded = expandedAssignee,
                        onDismissRequest = { expandedAssignee = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        assignees.forEach { assignee ->
                            DropdownMenuItem(
                                text = { Text(assignee.name) },
                                onClick = {
                                    selectedAssignee = assignee
                                    expandedAssignee = false
                                }
                            )
                        }
                    }
                }
            }

            item {
                ExposedDropdownMenuBox(
                    expanded = expandedLocation,
                    onExpandedChange = { expandedLocation = !expandedLocation }
                ) {
                    TextField(
                        readOnly = true,
                        value = selectedLocation?.location ?: "Select Device Location",
                        onValueChange = {},
                        label = { Text("Device Location") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .menuAnchor()
                    )

                    DropdownMenu(
                        expanded = expandedLocation,
                        onDismissRequest = { expandedLocation = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        locations.forEach { location ->
                            DropdownMenuItem(
                                text = { Text(location.location) },
                                onClick = {
                                    selectedLocation = location
                                    expandedLocation = false
                                }
                            )
                        }
                    }
                }
            }

            item {
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onClick = { datePickerVisible = true }
                ) {
                    Text(text = "Select Date")
                }
            }

            item {
                Text(
                    text = "Selected Date: ${convertLongDates(selectedDate)}",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .wrapContentSize(Alignment.Center),
                    fontSize = 24.sp
                )
            }
        }

        if (datePickerVisible) {
            DatePickerDialog(
                onDismissRequest = { datePickerVisible = false },
                confirmButton = {
                    TextButton(onClick = {
                        selectedDate = dataState.selectedDateMillis ?: selectedDate
                        datePickerVisible = false
                    }) {
                        Text(text = "Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        datePickerVisible = false
                    }) {
                        Text(text = "No")
                    }
                }
            ) {
                DatePicker(state = dataState)
            }
        }

        Spacer(modifier = Modifier.size(15.dp))

        OutlinedButton(
            onClick = {
                if (deviceName.isNotBlank() && deviceSerialNo.isNotBlank() &&
                    selectedAssignee != null && selectedDeviceType != null &&
                    selectedLocation != null) {

//                    val deviceQrCode = DeviceQrCodes(
//                        deviceType = selectedDeviceType!!.type,
//                        deviceSN = deviceSerialNo,
//                        deviceAssignee = selectedAssignee!!.name,
//                        deviceQr = null,
//                        date = convertLongDates(selectedDate),
//                        location = selectedLocation!!.location,
//                        color = null
//                    )
//                    deviceQrCodesViewModel.addDeviceQrCode(deviceQrCode)

                    // Navigate back to the previous screen
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = deviceSerialNo.isNotBlank() && selectedDeviceType != null &&
                    selectedAssignee != null  && selectedLocation != null
        ) {
            Text("Create QR")
        }
    }
}

private fun convertLongDates(date: Long): String {
    val dateNew = Date(date)
    val format = SimpleDateFormat.getDateInstance()
    return format.format(dateNew)
}
