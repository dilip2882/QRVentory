package com.dilip.qrventory.presentation.devices.qr_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.domain.models.QrCode
import com.dilip.domain.use_case.AppUseCases
import com.dilip.domain.util.OrderType
import com.dilip.domain.util.QrCodeOrder
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class QrCodesViewModel @Inject constructor(
    private val appUseCases: AppUseCases
) : ViewModel() {

    private val _state = mutableStateOf(QrCodesState())
    val state: State<QrCodesState> = _state

    private var recentlyDeletedQrCode: QrCode? = null

    private var getQrCodesJob: Job? = null

    init {
        getQrCodes(QrCodeOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: QrCodesEvent) {
        when (event) {
            is QrCodesEvent.Order -> {
                if (
                    state.value.qrCodeOrder::class == event.qrCodeOrder::class &&
                    state.value.qrCodeOrder.orderType == event.qrCodeOrder.orderType
                ) {
                    return
                }
                getQrCodes(event.qrCodeOrder)
            }

            is QrCodesEvent.DeleteQrCode -> {
                viewModelScope.launch {
                    appUseCases.deleteQrCodeUseCase(event.qrCode)
                    recentlyDeletedQrCode = event.qrCode
                }
            }

            is QrCodesEvent.RestoreQrCode -> {
                viewModelScope.launch {
                    appUseCases.addNoteUseCases(recentlyDeletedQrCode ?: return@launch)
                    recentlyDeletedQrCode = null
                }

            }

            is QrCodesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getQrCodes(qrCodeOrder: QrCodeOrder) {
        getQrCodesJob?.cancel()
        getQrCodesJob = appUseCases.getQrCodeUseCase(qrCodeOrder)
            .onEach { qrCodes ->
                _state.value = state.value.copy(
                    qrCodes = qrCodes,
                    qrCodeOrder = qrCodeOrder
                )
            }
            .launchIn(viewModelScope)
    }
}