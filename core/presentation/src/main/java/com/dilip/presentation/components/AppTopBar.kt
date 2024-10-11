package com.dilip.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.dilip.common.R

@Composable
fun AppTopBar(
    items: List<NavigationItem>,
    selectedItem: Int,
    onNewDeviceClick: () -> Unit,
) {
    val isOverflowExpanded = remember { mutableStateOf(false) }
    val searchState = remember { mutableStateOf("") }
    val isSearchActive = remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        title = {
            if (isSearchActive.value) {
                SearchBar(
                    query = searchState.value,
                    onQueryChange = { searchState.value = it },
                    onSearch = { isSearchActive.value = false },
                    active = isSearchActive.value,
                    onActiveChange = { isSearchActive.value = it },
                    placeholder = {
                        Text(text = if (selectedItem == 1) "Search Devices" else "Search Settings")
                    },
                    leadingIcon = {
                        IconButton(onClick = { isSearchActive.value = false }) {
                            if (isSearchActive.value) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                    contentDescription = "Back icon"
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search Icon"
                                )
                            }
                        }
                    },
                    trailingIcon = {
                        if (isSearchActive.value && searchState.value.isNotEmpty()) {
                            IconButton(onClick = { searchState.value = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear Icon"
                                )
                            }
                        }
                    }
                )
            } else {
                Text(text = items[selectedItem].text)
            }
        },
        actions = {
            Row {
                if (!isSearchActive.value && selectedItem == 1) {
                    IconButton(onClick = { isSearchActive.value = true }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                    }
                }

                if (selectedItem == 1) {
                    IconButton(onClick = { isOverflowExpanded.value = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_overflow_24dp),
                            contentDescription = "Overflow"
                        )
                    }
                }

                DropdownMenu(
                    expanded = isOverflowExpanded.value,
                    onDismissRequest = { isOverflowExpanded.value = false },
                    shape = RoundedCornerShape(16.dp),
                    offset = DpOffset(x = (-9).dp, y = 0.dp)
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Add a New Device") },
                        onClick = { onNewDeviceClick() },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Devices,
                                contentDescription = "Import Icon"
                            )
                        }
                    )
                }
            }
        }
    )
}
