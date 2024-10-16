package com.dilip.qrventory.presentation.devices.qr_chooser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dilip.qrventory.presentation.devices.qr_chooser.components.CustomExposedDropdownMenu
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceForm(
    onSubmit: (String, String, String, String, LocalDate) -> Unit
) {
    var deviceModel by remember { mutableStateOf("") }
    var deviceSN by remember { mutableStateOf("") }
    var assignee by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    val datePickerDialogState = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Device Model
        Text(
            text = "Device Model",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        CustomExposedDropdownMenu(
            selectedValue = deviceModel,
            options = listOf("Model 1", "Model 2", "Model 3"),
            onValueChange = { deviceModel = it },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Device Serial Number
        TextField(
            value = deviceSN,
            onValueChange = { deviceSN = it },
            label = { Text("Device Serial Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Assignee
        Text(
            text = "Assignee",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        CustomExposedDropdownMenu(
            selectedValue = assignee,
            options = listOf("Assignee 1", "Assignee 2", "Assignee 3"),
            onValueChange = { assignee = it },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Location
        Text(
            text = "Location",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        CustomExposedDropdownMenu(
            selectedValue = location,
            options = listOf("Location 1", "Location 2", "Location 3"),
            onValueChange = { location = it },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Date Picker
        Text(
            text = "Date",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Button(
            onClick = { datePickerDialogState.value = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = selectedDate?.toString() ?: "Select Date", color = MaterialTheme.colorScheme.onPrimary)
        }

        if (datePickerDialogState.value) {
            AlertDialog(
                onDismissRequest = { datePickerDialogState.value = false },
                title = { Text("Select Date") },
                text = {
                    // Include your date picker UI here
                    Text("Date Picker Placeholder")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            selectedDate = LocalDate.now() // Replace with actual date
                            datePickerDialogState.value = false
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { datePickerDialogState.value = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        // Submit Button
        Button(
            onClick = {
                onSubmit(
                    deviceModel,
                    deviceSN,
                    assignee,
                    location,
                    selectedDate ?: LocalDate.now()
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Generate QR", color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeviceFormPreview() {
    DeviceForm(
        onSubmit = { model, sn, assignee, location, date ->
        }
    )
}
