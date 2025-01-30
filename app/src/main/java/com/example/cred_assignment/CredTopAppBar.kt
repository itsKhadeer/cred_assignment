package com.example.cred_assignment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CredTopAppBar() {
    TopAppBar(modifier = Modifier.padding(horizontal = 20.dp),
        title = { Text(text = "") },
        navigationIcon = {
            Icon(
                Icons.Default.Close,
                "close",
                modifier = Modifier
                    .padding(5.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray.copy(alpha = 0.5f))
                    .padding(5.dp)
            )
        },
        actions = {
            Icon(
                Icons.Outlined.Info,
                "close",
                modifier = Modifier
                    .padding(5.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray.copy(alpha = 0.5f))
                    .padding(5.dp)
            )
        })
}