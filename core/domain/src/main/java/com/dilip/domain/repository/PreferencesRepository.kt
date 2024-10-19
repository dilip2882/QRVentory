package com.dilip.domain.repository

import com.dilip.domain.models.PreferencesSettings
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    suspend fun updateStorageLocation(uri: String)
    fun preferenceSettings(): Flow<PreferencesSettings>
}
