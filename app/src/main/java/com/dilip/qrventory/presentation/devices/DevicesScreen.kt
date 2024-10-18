package com.dilip.qrventory.presentation.devices

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dilip.qrventory.navigation.DevicesRouteScreen
import com.dilip.qrventory.presentation.devices.device_list.DeviceListItem
import java.io.ByteArrayInputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevicesScreen(
    navController: NavController
) {
    val viewModel: DevicesViewModel = hiltViewModel()
    val deviceList by viewModel.devicesQr.collectAsState(initial = emptyList())

    var qrCodeToShow by remember { mutableStateOf<ByteArray?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Devices") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(DevicesRouteScreen.AddDevice.route)
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Device")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(deviceList) { device ->
                DeviceListItem(
                    device = device,
                    onEditClick = {
                        navController.navigate("edit_device/${device.deviceSN}")
                    },
                    onDeleteClick = {
                        viewModel.deleteDeviceQr(device)
                    },
                    onQrClick = {
                        // Set the QR code to show
                        qrCodeToShow = device.deviceQr
                    }
                )
            }
        }

        // Show QR code popup if a QR code is set
        qrCodeToShow?.let {
            showQrCodePopup(qrCodeToShow) {
                qrCodeToShow = null
            }
        }
    }
}

@Composable
fun showQrCodePopup(qrBytes: ByteArray?, onDismiss: () -> Unit) {
    qrBytes?.let { bytes ->
        val qrBitmap = byteArrayToBitmap(bytes)

        if (qrBitmap != null) {
            AlertDialog(
                onDismissRequest = { onDismiss() },
                title = { Text("QR Code") },
                text = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            bitmap = qrBitmap.asImageBitmap(),
                            contentDescription = "QR Code",
                            modifier = Modifier.size(200.dp)
                        )
                    }
                },
                confirmButton = {
                    OutlinedButton(onClick = onDismiss) {
                        Text("Close")
                    }
                }
            )
        }
    }
}

// Convert ByteArray to Bitmap
fun byteArrayToBitmap(byteArray: ByteArray): Bitmap? {
    return ByteArrayInputStream(byteArray).use { inputStream ->
        BitmapFactory.decodeStream(inputStream)
    }
}