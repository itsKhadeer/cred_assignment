package com.example.cred_assignment.presentation.secondview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cred_assignment.domain.models.SecondViewContent
import io.ktor.util.toUpperCasePreservingASCIIRules

@Composable
fun SecondViewCollapsedContent(
    content: SecondViewContent,
    plan: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.LightGray.copy(alpha = 0.5f))
            .clip(
                RoundedCornerShape(
                    30.dp, 30.dp, 0.dp, 0.dp
                )
            )
            .background(Color.LightGray.copy(alpha = 0.8f))

            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(0.7f)

        ) {
            Column {
                Text(text = content.closedState.key1.toUpperCasePreservingASCIIRules())
                Text(
                    text = content.openState.items[plan].emi,
                    modifier = Modifier.wrapContentWidth()
                )
            }
            Column {
                Text(text = content.closedState.key2)
                Text(
                    text = content.openState.items[plan].duration,
                    modifier = Modifier.wrapContentWidth()
                )
            }
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "cancel",
            modifier = Modifier.padding(5.dp)
        )
    }
}