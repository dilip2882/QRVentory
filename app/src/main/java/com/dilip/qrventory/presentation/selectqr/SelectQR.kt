package com.dilip.qrventory.presentation.selectqr

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectQR(rootNavController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("YourPrefsName", Context.MODE_PRIVATE)

    // State to manage selected option
    var selectedOption by remember { mutableStateOf(sharedPreferences.getString("selected_option", "") ?: "") }
    var tempSelectedOption by remember { mutableStateOf("") } // Temporary selection

    // Options for the dialog
    val options = listOf("MLKit Camera Scanner", "ZXing Scanner", "Google Code Scanner")

    // Save selected option in SharedPreferences
    fun saveSelectedOption(option: String) {
        sharedPreferences.edit().putString("selected_option", option).apply()
        Toast.makeText(context, "Selected: $option", Toast.LENGTH_SHORT).show() // Show toast
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
                title = { Text(text = "Select QR Settings") },
                navigationIcon = {
                    IconButton(
                        onClick = { rootNavController.navigateUp() }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
            )
        }
    ) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                // Card with radio buttons and button
                Card(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = MaterialTheme.shapes.medium // Rounded corners
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        // Text in the card
                        Text(
                            text = "Select from the below QR Settings",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Displaying radio buttons
                        options.forEach { option ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        tempSelectedOption = option // Update temporary selection
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (tempSelectedOption == option),
                                    onClick = { tempSelectedOption = option } // Update temporary selection on radio button click
                                )
                                Text(
                                    text = option,
                                    modifier = Modifier.padding(start = 8.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Button to save selection
                        Button(
                            onClick = {
                                if (tempSelectedOption.isNotEmpty()) {
                                    saveSelectedOption(tempSelectedOption) // Save option to SharedPreferences
                                    selectedOption = tempSelectedOption // Update displayed option
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Save Selection")
                        }
                    }
                }
            }

            item {
                // Currently Selected Settings Card
                Card(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Currently Selected Settings Text
                        Text(
                            text = "Currently Selected Settings:",
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)),
                            modifier = Modifier.padding(bottom = 4.dp) // Adjust padding as necessary
                        )

                        // Selected Option Text
                        Text(
                            text = selectedOption.takeIf { it.isNotEmpty() } ?: "None selected",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(4.dp) // Adjust padding as necessary
                        )
                    }
                }
            }
        }
    }
}
