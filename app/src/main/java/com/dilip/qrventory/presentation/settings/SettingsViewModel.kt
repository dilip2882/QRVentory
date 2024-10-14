package com.dilip.qrventory.presentation.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.domain.repository.PreferencesRepository
import com.dilip.domain.models.PreferencesSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val preferencesRepository: PreferencesRepository,
    application: Application
) : AndroidViewModel(application) {
    private val _preferencesSettings = MutableStateFlow<PreferencesSettings?>(null)
    val preferencesSettings: StateFlow<PreferencesSettings?> = _preferencesSettings

    init {
        viewModelScope.launch {
            preferencesRepository.preferenceSettings().collectLatest {
                _preferencesSettings.value = it
            }
        }
    }

    fun updateStorageLocation(string: String) {
        viewModelScope.launch {
            preferencesRepository.updateStorageLocation(string)
        }
    }
}