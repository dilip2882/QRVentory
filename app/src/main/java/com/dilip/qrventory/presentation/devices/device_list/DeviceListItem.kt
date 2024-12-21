package com.dilip.qrventory.presentation.devices.device_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dilip.domain.models.device.DeviceQr
import com.dilip.qrventory.presentation.devices.byteArrayToBitmap

@Composable
fun DeviceListItem(
    device: DeviceQr,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onQrClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Device Type: ${device.deviceType}")
                Text(text = "Serial No: ${device.deviceSN}")
                Text(text = "Assignee: ${device.deviceAssignee}")
                Text(text = "Date: ${device.date}")
                Text(text = "Location: ${device.location}")

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    OutlinedButton(
                        onClick = onEditClick,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text("Edit")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedButton(
                        onClick = onDeleteClick,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                    ) {
                        Text("Delete")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

//                    OutlinedButton(
//                        onClick = onQrClick,
//                        modifier = Modifier.weight(1f)
//                    ) {
//                        Text("View QR")
//                    }
                }
            }

            // Display the QR code image if it exists
            device.deviceQr?.let { qrBytes ->
                val qrBitmap = byteArrayToBitmap(qrBytes) // Convert QR bytes to Bitmap
                qrBitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "QR Code",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(start = 16.dp)
                            .align(Alignment.CenterVertically)
                            .clickable(onClick = onQrClick),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DeviceListItemPreview() {
    DeviceListItem(
        device = DeviceQr(
            deviceType = "Camera",
            deviceSN = "SN123456",
            deviceAssignee = "John Doe",
            date = "2023-10-18",
            location = "Building A",
            deviceQr = null,
        ),
        onEditClick = {},
        onDeleteClick = {},
        onQrClick = {},
    )
}
