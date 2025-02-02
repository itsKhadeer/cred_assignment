package com.example.cred_assignment.presentation.secondview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondViewDragHandler(
    key1: String,
    amount: Int
) {
    Row(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(
                    30.dp, 30.dp, 0.dp, 0.dp
                )
            )
            .background(Color.LightGray.copy(alpha = 0.5f))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.alpha(0.5f)) {
            Text(text = key1)
            Text(
                fontSize = 24.sp, text = "₹${amount}", fontWeight = FontWeight.Bold
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "cancel",
            modifier = Modifier.padding(5.dp)
        )
    }
}