package com.dilip.qrventory.presentation.authentication

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dilip.qrventory.R

@Composable
fun AuthScreen(onLogin: (String) -> Unit) {
    var flip by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density
    val rotationY by animateFloatAsState(
        targetValue = if (flip) 180f else 0f,
        animationSpec = tween(durationMillis = 600),
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            modifier = Modifier
                .padding(25.dp)
                .fillMaxSize()
                .graphicsLayer(rotationY = rotationY, cameraDistance = 12 * density)
                .padding(4.dp),
            contentAlignment = Alignment.Center,
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                if (flip) {
                    RegistrationPage(
                        onRegister = { phoneNumber ->
                            println("Registration successful for phone: $phoneNumber")
                            flip = false
                        },
                        onLogin = { flip = false },
                        reversed = true,
                    )
                } else {
                    LoginPage(
                        onLogin = onLogin,
                        onSignUp = { flip = true },
                    )
                }
            }
        }
    }
}
