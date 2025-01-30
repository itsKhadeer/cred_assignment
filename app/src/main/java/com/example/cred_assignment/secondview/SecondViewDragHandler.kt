package com.example.cred_assignment.secondview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cred_assignment.models.Content
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondViewDragHandler(
    scope: CoroutineScope,
    modelBottomSheetState1: SheetState,
    changeState: () -> Unit,
    content: Content,
    amount: Int
) {
    Row(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(
                    30.dp, 30.dp, 0.dp, 0.dp
                )
            )
            .background(Color(0xFF12181F))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp)
            .clickable {
                scope.launch {
                    modelBottomSheetState1.hide()
                }.invokeOnCompletion { changeState() }
            }, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.alpha(0.5f)) {
            Text(text = content.items[0].closedState.body.key1!!)
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