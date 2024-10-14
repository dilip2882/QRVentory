package com.dilip.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dilip.domain.repository.PreferencesRepository
import com.dilip.domain.models.PreferencesSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "appPreferences")

class PreferencesDatastore @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferencesRepository {

    private object PreferencesKeys {
        val STORAGE_LOCATION = stringPreferencesKey("storageLocation")
    }

    private val datastore = context.dataStore

    override suspend fun updateStorageLocation(uri: String) {
        datastore.edit {
            it[PreferencesKeys.STORAGE_LOCATION] = uri
        }
    }

    override fun preferenceSettings(): Flow<PreferencesSettings> {
        return datastore.data.catch {
            emit(emptyPreferences())
        }.map { preferences ->

            val storageLocation = preferences[PreferencesKeys.STORAGE_LOCATION] ?: ""
            PreferencesSettings(storageLocation)
        }
    }

}