package com.dilip.qrventory.presentation.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun updateUserName(input: String) {
        uiState.username = uiState.copy(username = input).toString()
    }

    fun updateEmail(input: String) {
        uiState.email = uiState.copy(email = input).toString()
    }

    fun updatePassword(input: String) {
        uiState.password = uiState.copy(password = input).toString()
    }
}

data class SignUpUiState(
    var username: String = "",
    var email: String = "",
    var password: String = "",
)
