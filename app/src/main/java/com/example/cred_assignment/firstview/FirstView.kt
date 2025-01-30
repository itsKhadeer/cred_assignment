package com.example.cred_assignment.firstview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cred_assignment.models.Content

@Composable
fun FirstView(
    paddingValues: PaddingValues,
    content: Content,
    changeBottomSheet1: () -> Unit,
    changeAmount: (Int) -> Unit,
    amount: Int
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier)
        Column(
            Modifier.padding(20.dp)
        ) {
            Text(
                content.items[0].openState.body.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.W300
            )
            Text(
                content.items[0].openState.body.subtitle,
                fontSize = 14.sp,
                color = Color.Gray.copy(0.9f)
            )
            Spacer(Modifier.height(20.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    Modifier.aspectRatio(1f), contentAlignment = Alignment.Center
                ) {
                    CustomCircularSlider(
                        changeAmount = { changeAmount(it) },
                        maxValue = content.items[0].openState.body.card?.maxRange!!,
                        minValue = content.items[0].openState.body.card?.minRange!!,
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = content.items[0].openState.body.card?.header!!,
                            color = Color.Gray
                        )
                        Text(
                            fontSize = 24.sp,
                            text = "₹${amount}",
                            textDecoration = TextDecoration.Underline,
                            color = Color.DarkGray.copy(alpha = 0.85f),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            color = Color.Green.copy(green = 0.8f),
                            text = content.items[0].openState.body.card?.description!!
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    text = content.items[0].openState.body.footer,
                    textAlign = TextAlign.Center,
                    color = Color.Gray.copy(0.7f),
                    fontSize = 14.sp
                )
            }
        }

        Button(
            onClick = { changeBottomSheet1() },
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = Color(0xFF37439C), contentColor = Color.White
            )
        ) {
            Text(text = content.items[0].ctaText)
        }

    }
}
