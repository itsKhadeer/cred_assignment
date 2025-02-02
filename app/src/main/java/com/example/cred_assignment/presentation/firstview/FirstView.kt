package com.example.cred_assignment.presentation.firstview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.cred_assignment.data.datasource.api.entity.Content
import com.example.cred_assignment.domain.models.ContentModel
import com.example.cred_assignment.domain.models.FirstViewContent

@Composable
fun FirstView(
    paddingValues: PaddingValues,
    content: FirstViewContent,
    changeAmount: (Int) -> Unit,
    amount: Int
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier)
        Column(
            Modifier.padding(20.dp)
        ) {
            Text(
                content.openState.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.W300
            )
            Text(
                content.openState.subtitle,
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
                        maxValue = content.openState.card.maxRange,
                        minValue = content.openState.card.minRange,
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = content.openState.card.header,
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
                            text = content.openState.card.description
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    text = content.openState.footer,
                    textAlign = TextAlign.Center,
                    color = Color.Gray.copy(0.7f),
                    fontSize = 14.sp
                )
            }
        }
    }
}
