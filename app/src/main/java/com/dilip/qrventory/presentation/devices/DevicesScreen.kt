package com.dilip.qrventory.presentation.devices

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dilip.qrventory.navigation.DevicesRouteScreen
import com.dilip.qrventory.presentation.devices.device_list.DeviceListItem
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevicesScreen(
    navController: NavController,
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
                },
            )
        },
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.padding(horizontal = 16.dp),
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
                    },
                )
            }
        }

        // Show QR code popup if a QR code is set
        qrCodeToShow?.let {
            ShowQrCodePopup(qrCodeToShow) {
                qrCodeToShow = null
            }
        }
    }
}

@Composable
fun ShowQrCodePopup(
    qrBytes: ByteArray?,
    onDismiss: () -> Unit,
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    qrBytes?.let { bytes ->
        val qrBitmap = byteArrayToBitmap(bytes)

        if (qrBitmap != null) {
            AlertDialog(
                onDismissRequest = { onDismiss() },
                title = { Text("QR Code") },
                text = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            bitmap = qrBitmap.asImageBitmap(),
                            contentDescription = "QR Code",
                            modifier = Modifier.size(200.dp),
                        )
                    }
                },
                confirmButton = {
                    OutlinedButton(onClick = onDismiss) {
                        Text("Close")
                    }
                },
                dismissButton = {
                    Column {
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            DropdownMenuItem(
                                text = { Text("Save to Gallery") },
                                onClick = {
                                    expanded = false
                                    saveQrToGallery(context, qrBitmap)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                            )
                            DropdownMenuItem(
                                text = { Text("Share as Image") },
                                onClick = {
                                    expanded = false
                                    shareQrImage(context, qrBitmap)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                            )
                            DropdownMenuItem(
                                text = { Text("Share as Text") },
                                onClick = {
                                    expanded = false
                                    shareQrText(context, bytes)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                            )
                        }

                        // Center the Share button
                        Box(
                            modifier = Modifier.padding(
                                end = 15.dp,
                            ),
                            contentAlignment = Alignment.Center,
                        ) {
                            IconButton(
                                onClick = { expanded = true },
                            ) {
                                Icon(Icons.Default.Share, contentDescription = "Share")
                            }
                        }
                    }
                },
            )
        }
    }
}

// Save the QR code image to gallery
fun saveQrToGallery(context: Context, qrBitmap: Bitmap) {
    val savedImageURL = MediaStore.Images.Media.insertImage(
        context.contentResolver,
        qrBitmap,
        "QR_Code",
        "QR code image",
    )
    Toast.makeText(context, "QR code saved to Gallery", Toast.LENGTH_SHORT).show()
}

// Share the QR code as an image
fun shareQrImage(context: Context, qrBitmap: Bitmap) {
    val file = File(context.cacheDir, "shared_qr.png")
    val fos = FileOutputStream(file)
    qrBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
    fos.flush()
    fos.close()
    val uri = FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)

    val intent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/png"
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(intent, "Share QR Code"))
}

// Share QR code as text
fun shareQrText(context: Context, qrBytes: ByteArray) {
    val qrText = String(qrBytes)
    val intent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, qrText)
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(intent, "Share QR Code Text"))
}

// Convert ByteArray to Bitmap
fun byteArrayToBitmap(byteArray: ByteArray): Bitmap? {
    return ByteArrayInputStream(byteArray).use { inputStream ->
        BitmapFactory.decodeStream(inputStream)
    }
}
