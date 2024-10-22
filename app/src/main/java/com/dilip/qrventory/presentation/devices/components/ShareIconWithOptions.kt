package com.dilip.qrventory.presentation.devices.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShareIconWithOptions(
    onSaveToGallery: () -> Unit,
    onShareAsImage: () -> Unit,
    onShareAsText: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share Icon",
                modifier = Modifier.size(24.dp),
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            DropdownMenuItem(
                text = { Text("Save to Gallery") },
                onClick = {
                    expanded = false
                    onSaveToGallery()
                },
            )

            DropdownMenuItem(
                text = { Text("Share as Image") },
                onClick = {
                    expanded = false
                    onShareAsImage()
                },
            )

            DropdownMenuItem(
                text = { Text("Share as Text") },
                onClick = {
                    expanded = false
                    onShareAsText()
                },
            )
        }
    }
}
