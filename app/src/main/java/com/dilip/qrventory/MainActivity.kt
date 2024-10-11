package com.dilip.qrventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dilip.presentation.splash.SplashScreen
import com.dilip.qrventory.ui.theme.QRVentoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QRVentoryTheme {
                SplashScreen()
//                EmptyScreen(message = "")
            }
        }
    }
}




