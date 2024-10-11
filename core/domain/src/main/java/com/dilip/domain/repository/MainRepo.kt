package com.dilip.domain.repository

import kotlinx.coroutines.flow.Flow

interface MainRepo {
    fun startScanning(): Flow<String?>
}