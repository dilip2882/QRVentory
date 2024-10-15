package com.dilip.qrventory.presentation.devices.add_edit_qr

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.domain.models.InvalidQrCodeException
import com.dilip.domain.models.QrCode
import com.dilip.domain.use_case.AppUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditQrCodeViewModel @Inject constructor(
    private val appUseCases: AppUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _qrCodeTitle = mutableStateOf(
        QrCodeTextFieldState(
            hint = "Enter text..."
        )
    )
    val qrCodeTitle: State<QrCodeTextFieldState> = _qrCodeTitle

    private val _qrCodeContent = mutableStateOf(
        QrCodeTextFieldState(
            hint = "Enter content"
        )
    )
    val qrCodeContent: State<QrCodeTextFieldState> = _qrCodeContent

    private val _qrCodeColor = mutableStateOf(QrCode.qrCodeColors.random().toArgb())
    val qrCodeColor: State<Int> = _qrCodeColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFLow = _eventFlow.asSharedFlow()

    private var currentQrCodeId: Int? = null

    init {
        savedStateHandle.get<Int>("qrCodeId")?.let { qrCodeId ->
            if (qrCodeId != -1) {
                viewModelScope.launch {
                    appUseCases.getQrCodeUseCase(qrCodeId)?.also { qrCode ->
                        currentQrCodeId = qrCode.id
                        _qrCodeTitle.value = qrCodeTitle.value.copy(
                            text = qrCode.title,
                            isHintVisible = false
                        )
                        _qrCodeContent.value = _qrCodeContent.value.copy(
                            text = qrCode.content,
                            isHintVisible = false
                        )
                        _qrCodeColor.value = qrCode.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditQrCodeEvent) {
        when (event) {
            is AddEditQrCodeEvent.EnteredTitle -> {
                _qrCodeTitle.value = qrCodeTitle.value.copy(
                    text = event.value
                )
            }

            is AddEditQrCodeEvent.ChangeTitleFocus -> {
                _qrCodeTitle.value = qrCodeTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            qrCodeTitle.value.text.isBlank()
                )
            }

            is AddEditQrCodeEvent.EnteredContent -> {
                _qrCodeContent.value = _qrCodeContent.value.copy(
                    text = event.value
                )
            }

            is AddEditQrCodeEvent.ChangeContentFocus -> {
                _qrCodeContent.value = _qrCodeContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _qrCodeContent.value.text.isBlank()
                )
            }

            is AddEditQrCodeEvent.ChangeColor -> {
                _qrCodeColor.value = event.color
            }


            is AddEditQrCodeEvent.SaveQrCode -> {
                viewModelScope.launch {
                    try {
                        appUseCases.addQrCodeUseCases(
                            QrCode(
                                title = qrCodeTitle.value.text,
                                content = qrCodeContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = qrCodeColor.value,
                                id = currentQrCodeId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveQrCode)
                    } catch (e: InvalidQrCodeException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save Qr Code"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveQrCode : UiEvent()
    }

}