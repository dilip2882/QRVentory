package com.dilip.qrventory.presentation.devices.add_edit_qr

import androidx.compose.ui.focus.FocusState

sealed class AddEditQrCodeEvent{
    data class EnteredTitle(val value: String): AddEditQrCodeEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditQrCodeEvent()
    data class EnteredContent(val value: String): AddEditQrCodeEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditQrCodeEvent()
    data class ChangeColor(val color: Int): AddEditQrCodeEvent()
    object SaveQrCode: AddEditQrCodeEvent()
}

