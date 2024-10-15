package com.dilip.qrventory.presentation.devices.add_edit_qr

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dilip.domain.models.QrCode
import com.dilip.qrventory.presentation.devices.add_edit_qr.components.IconRow
import com.dilip.qrventory.presentation.devices.add_edit_qr.components.QrScannerHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditQRCodeScreen(
    navController: NavController,
    qrCodeColor: Int,
    onBackClick: () -> Unit,
    onSaveClick: (String, String) -> Unit
) {
    val viewModel: AddEditQrCodeViewModel = hiltViewModel()
    val titleState = viewModel.qrCodeTitle.value
    val contentState = viewModel.qrCodeContent.value

    val qrCodeBackgroundAnimatable = remember {
        Animatable(
            Color(if (qrCodeColor != -1) qrCodeColor else viewModel.qrCodeColor.value)
        )
    }
    val scope = rememberCoroutineScope()

    // State for Snackbar
    var snackbarMessage by remember { mutableStateOf("") }
    var showSnackbar by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.eventFLow.collectLatest { event ->
            when (event) {
                is AddEditQrCodeViewModel.UiEvent.ShowSnackbar -> {
                    snackbarMessage = event.message
                    showSnackbar = true
                }

                is AddEditQrCodeViewModel.UiEvent.SaveQrCode -> {
                    navController.navigateUp()
                }
            }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit QR Code") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditQrCodeEvent.SaveQrCode)
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Save, contentDescription = "Save")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(qrCodeBackgroundAnimatable.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                QrCode.qrCodeColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.qrCodeColor.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    qrCodeBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(durationMillis = 500)
                                    )
                                }
                                viewModel.onEvent(AddEditQrCodeEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                // Row of social media icons
                IconRow()

                Spacer(modifier = Modifier.height(16.dp))

                QrScannerHintTextField(
                    text = titleState.text,
                    hint = titleState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditQrCodeEvent.EnteredTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditQrCodeEvent.ChangeTitleFocus(it))
                    },
                    isHintVisible = true,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                QrScannerHintTextField(
                    text = contentState.text,
                    hint = contentState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditQrCodeEvent.EnteredContent(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditQrCodeEvent.ChangeContentFocus(it))
                    },
                    isHintVisible = contentState.isHintVisible,
                    textStyle = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxHeight(),
                )

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EditQRCodeScreenPreview() {
    val navController = rememberNavController()
    val qrCodeColor = Color.Red.toArgb()
    val onBackClick = { }
    val onSaveClick: (String, String) -> Unit = { title, link ->

    }

    EditQRCodeScreen(
        navController = navController,
        qrCodeColor = qrCodeColor,
        onBackClick = onBackClick,
        onSaveClick = onSaveClick
    )
}
