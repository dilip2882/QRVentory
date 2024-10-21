package com.dilip.domain.repository

import kotlinx.coroutines.flow.Flow

interface QRScannerRepository {
    fun startScanning(): Flow<String?>
}
