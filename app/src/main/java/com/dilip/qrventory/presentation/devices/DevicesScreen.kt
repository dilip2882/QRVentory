package com.dilip.qrventory.presentation.devices

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dilip.qrventory.presentation.devices.components.QRCodeGenerator

@Composable
fun DevicesScreen(
    rootNavController: NavHostController
) {
    QRCodeGenerator()
}