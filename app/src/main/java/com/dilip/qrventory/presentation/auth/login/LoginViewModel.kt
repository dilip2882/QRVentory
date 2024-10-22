package com.dilip.qrventory.presentation.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginScreenViewModel : ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    fun updateEmail(input: String) {
        uiState.email = uiState.copy(email = input).toString()
    }

    fun updatePassword(input: String) {
        uiState.password = uiState.copy(password = input).toString()
    }
}

data class LoginUiState(
    var email: String = "",
    var password: String = "",
)
