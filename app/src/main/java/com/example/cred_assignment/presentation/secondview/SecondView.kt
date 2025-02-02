package com.example.cred_assignment.presentation.secondview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cred_assignment.domain.models.SecondViewContent

@Composable
fun SecondView(
    content: SecondViewContent,
    plan: Int,
    changePlan: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.5f))
            .padding(top = 30.dp, start = 20.dp)
    ) {
        Text(
            text = content.openState.title,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = content.openState.subtitle,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            color = Color.Gray.copy(0.5f)
        )
        Spacer(modifier = Modifier.height(20.dp))

        LazyRow {
            val colors = listOf(
                Color(0xFF42323A),
                Color(0xFF7A718D),
                Color(0xFF536B8C),
            )
            content.openState.items.let { card ->
                items(card.size) {
                    Box(
                        Modifier
                            .fillMaxSize(0.35f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(colors[it % colors.size])
                            .aspectRatio(1f)
                            .padding(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Checkbox(
                                it == plan,
                                onCheckedChange = { _ -> changePlan(it) },
                            )
                            Text(text = card[it].title, fontSize = 14.sp, color = Color.White)
                            Text(
                                text = card[it].subtitle,
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.5f)
                            )
                        }
                    }
                    Spacer(Modifier.width(10.dp))
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        OutlinedButton(
            onClick = {},
            colors = ButtonDefaults.outlinedButtonColors().copy(
                contentColor = Color.Black,
                containerColor = Color.White
            )
        ) { Text(text = content.openState.footer) }
    }

}