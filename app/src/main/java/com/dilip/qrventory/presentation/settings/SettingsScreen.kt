package com.dilip.qrventory.presentation.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.DevicesOther
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dilip.common.storage.getReadablePathFromUri
import com.dilip.qrventory.navigation.SettingsRouteScreen
import com.dilip.qrventory.presentation.settings.components.LogoHeader
import com.dilip.qrventory.presentation.settings.components.TextPreferenceWidget

@Composable
fun SettingsScreen(
    rootNavController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val viewModel: SettingsViewModel = hiltViewModel()
    val preferencesSettings by viewModel.preferencesSettings.collectAsState()

    val uriHandler = LocalUriHandler.current

    // State for each setting based on ViewModel's preferences
    val storageLocation =
        preferencesSettings?.storageLocation ?: "\"/storage/emulated/0/Downloads\""

    val pickStorageLocation = storageLocationPicker(viewModel)
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        item {
            LogoHeader()
        }

        item {
            TextPreferenceWidget(
                title = "Storage location",
                subtitle = getReadablePathFromUri(context, storageLocation),
                icon = Icons.Default.Storage,
                onPreferenceClick = {
                    try {
                        pickStorageLocation.launch(null)
                    } catch (_: ActivityNotFoundException) {
                    }
                },
            )
        }

        item {
            HorizontalDivider()
        }

        item {
            TextPreferenceWidget(
                title = "Device Assignee",
                icon = Icons.Default.Person,
                onPreferenceClick = {
                    rootNavController.navigate(SettingsRouteScreen.DeviceAssignee.route)
                },
            )
        }

        item {
            TextPreferenceWidget(
                title = "Device Location",
                icon = Icons.Filled.LocationOn,
                onPreferenceClick = {
                    rootNavController.navigate(SettingsRouteScreen.DeviceLocation.route)
                },
            )
        }

        item {
            TextPreferenceWidget(
                title = "Device Type",
                icon = Icons.Filled.DevicesOther,
                onPreferenceClick = {
                    rootNavController.navigate(SettingsRouteScreen.DeviceType.route)
                },
            )
        }

        item {
            HorizontalDivider()
        }

        item {
            TextPreferenceWidget(
                title = "About",
                icon = Icons.Default.Info,
                onPreferenceClick = {
                    rootNavController.navigate(SettingsRouteScreen.AboutScreen.route)
                },
            )
        }

        item {
            TextPreferenceWidget(
                title = "Help",
                icon = Icons.AutoMirrored.Filled.Help,
                onPreferenceClick = {
                    uriHandler.openUri("https://github.com/dilip2882/QRVentory/blob/main/README.md")
                },
            )
        }
    }
}

@Composable
fun storageLocationPicker(viewModel: SettingsViewModel): ManagedActivityResultLauncher<Uri?, Uri?> {
    val context = LocalContext.current
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree(),
    ) { uri ->
        if (uri != null) {
            val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION

            // For some reason InkBook devices do not implement the SAF properly. Persistable URI grants do not
            // work. However, simply retrieving the URI and using it works fine for these devices. Access is not
            // revoked after the app is closed or the device is restarted.
            // This also holds for some Samsung devices. Thus, we simply execute inside of a try-catch block and
            // ignore the exception if it is thrown.
            try {
                context.contentResolver.takePersistableUriPermission(uri, flags)
            } catch (e: SecurityException) {
                Log.e("File Picker", "$e")
            }

            viewModel.updateStorageLocation(uri.toString())
        }
    }
}
