package com.dilip.qrventory.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.domain.repository.MainRepo
import com.dilip.qrventory.presentation.Scanner.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MainRepo
) : ViewModel() {
    private val _state = MutableStateFlow(ScreenState())
    val state = _state.asStateFlow()

    fun startScanning() {
        viewModelScope.launch {
            repo.startScanning().collect { data ->
                if (!data.isNullOrBlank()) {
                    _state.value = state.value.copy(
                        details = data
                    )
                }
            }
        }
    }
}

