package com.dilip.qrventory.presentation.profile.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dilip.common.Constants.PROFILE_SCREEN
import com.dilip.common.Constants.REVOKE_ACCESS
import com.dilip.common.Constants.SIGN_OUT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    signOut: () -> Unit,
    revokeAccess: () -> Unit,
) {
    var openMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(text = PROFILE_SCREEN)
        },
        actions = {
            IconButton(
                onClick = {
                    openMenu = !openMenu
                },
            ) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = null,
                )
            }

            DropdownMenu(
                expanded = openMenu,
                onDismissRequest = {
                    openMenu = false
                },
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = SIGN_OUT)
                    },
                    onClick = {
                        signOut()
                        openMenu = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Sign out")
                    },
                    trailingIcon = null,
                    enabled = true,
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    interactionSource = remember { MutableInteractionSource() },
                )

                DropdownMenuItem(
                    text = {
                        Text(text = REVOKE_ACCESS)
                    },
                    onClick = {
                        revokeAccess()
                        openMenu = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "Revoke Access")
                    },
                    trailingIcon = null,
                    enabled = true,
                    contentPadding = PaddingValues(horizontal = 16.dp),
                )
            }
        },
    )
}
