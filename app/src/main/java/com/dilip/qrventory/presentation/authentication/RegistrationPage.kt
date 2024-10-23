package com.dilip.qrventory.presentation.authentication

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dilip.qrventory.presentation.authentication.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun RegistrationPage(onRegister: (String) -> Unit, onLogin: () -> Unit, reversed: Boolean = false) {
    val context = LocalContext.current
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val density = LocalDensity.current.density
    val rotationY by animateFloatAsState(if (reversed) 180f else 0f, animationSpec = tween(durationMillis = 600))
    var phoneNumber by remember { mutableStateOf("+91") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    fun saveUserToFirestore(user: User) {
        val firestore = FirebaseFirestore.getInstance()
        val usersCollection = firestore.collection("users")

        usersCollection.document(user.phoneNumber).set(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                    onRegister(user.phoneNumber)
                } else {
                    Toast.makeText(context, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(464.dp)
            .graphicsLayer(rotationY = rotationY, cameraDistance = 12 * density)
            .background(Color.White.copy(alpha = 0.8f), shape = MaterialTheme.shapes.large)
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Register",
                fontSize = 32.sp,
                color = Color(0xFF1976D2),
                style = MaterialTheme.typography.headlineMedium,
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF1976D2),
                    unfocusedLabelColor = Color.Gray,
                ),
                shape = MaterialTheme.shapes.large,
                placeholder = { Text("Enter your name") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF1976D2),
                    unfocusedLabelColor = Color.Gray,
                ),
                shape = MaterialTheme.shapes.large,
                placeholder = { Text("Enter your email") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            )

            TextField(
                value = phoneNumber,
                onValueChange = {
                    if (it.startsWith("+91") || it.isEmpty()) {
                        phoneNumber = it
                    }
                },
                label = { Text("Phone Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color(0xFF1976D2),
                    unfocusedLabelColor = Color.Gray,
                ),
                shape = MaterialTheme.shapes.large,
                placeholder = { Text("Enter your phone number") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (name.isNotEmpty() && email.isNotEmpty() && phoneNumber.isNotEmpty()) {
                        saveUserToFirestore(User(phoneNumber, name, email))
                    } else {
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2), contentColor = Color.White),
                shape = MaterialTheme.shapes.large,
            ) {
                Text(text = "Register", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Already have an account? Login",
                color = Color(0xFF505D72),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .clickable(onClick = { onLogin() })
                    .padding(8.dp),
            )
        }
    }
}
