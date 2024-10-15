package com.dilip.qrventory.presentation.devices.add_edit_qr.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dilip.qrventory.ui.theme.QRVentoryTheme
import androidx.compose.material.icons.filled.*

@Composable
fun IconRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Facebook
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.Facebook,
                contentDescription = "Facebook",
                modifier = Modifier.size(48.dp),
                tint = Color.Blue
            )
        }
        // Contacts
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.Contacts,
                contentDescription = "Contacts",
                modifier = Modifier.size(48.dp),
                tint = Color.Green
            )
        }
        // Email
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.Email,
                contentDescription = "Email",
                modifier = Modifier.size(48.dp),
                tint = Color.Gray
            )
        }
        // Text Message
        IconButton(onClick = { }) {
            Icon(
                Icons.AutoMirrored.Filled.Message,
                contentDescription = "Text Message",
                modifier = Modifier.size(48.dp),
                tint = Color.Blue
            )
        }
        // Google
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.Search,  // Use search icon for Google
                contentDescription = "Google",
                modifier = Modifier.size(48.dp),
                tint = Color.Green
            )
        }
        // Discord
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.ChatBubble,
                contentDescription = "Discord",
                modifier = Modifier.size(48.dp),
                tint = Color.Blue
            )
        }
        // Call
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.Call,
                contentDescription = "Call",
                modifier = Modifier.size(48.dp),
                tint = Color.Green
            )
        }
        // Reddit
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.Forum,  // Forum icon as placeholder for Reddit
                contentDescription = "Reddit",
                modifier = Modifier.size(48.dp),
                tint = Color.Blue
            )
        }
        // Calendar
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.CalendarToday,
                contentDescription = "Calendar",
                modifier = Modifier.size(48.dp),
                tint = Color.Red
            )
        }
        // Dollar (Payment)
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.AttachMoney,
                contentDescription = "Payment",
                modifier = Modifier.size(48.dp),
                tint = Color.Green
            )
        }
        // Thumbs Up
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.ThumbUp,
                contentDescription = "Thumbs Up",
                modifier = Modifier.size(48.dp),
                tint = Color.Blue
            )
        }

        //
        
    }
}


@Preview(showBackground = true)
@Composable
fun IconRowPreVew() {
    QRVentoryTheme {
        IconRow()
    }
}