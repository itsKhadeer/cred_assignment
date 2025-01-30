package com.example.cred_assignment.thirdview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cred_assignment.models.Content
import io.ktor.util.toUpperCasePreservingASCIIRules
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdViewDragHandler(
    scope: CoroutineScope,
    changeState: () -> Unit,
    modelBottomSheetState2: SheetState,
    showBottomSheet2: Boolean,
    content: Content,
    plan: Int
) {
    Row(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(
                    30.dp, 30.dp, 0.dp, 0.dp
                )
            )
            .background(Color(0xFF171E27))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp)
            .alpha(0.5f)
            .clickable {
                scope.launch {
                    modelBottomSheetState2.hide()
                }.invokeOnCompletion {
                    changeState()
                }
            }, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(0.7f)

        ) {
            Column {
                Text(text = content.items[1].closedState.body.key1!!.toUpperCasePreservingASCIIRules())
                Text(
                    text = content.items[1].openState.body.items!![plan].emi!!,
                    modifier = Modifier.wrapContentWidth()
                )
            }
            Column {
                Text(text = content.items[1].closedState.body.key2!!)
                Text(
                    text = content.items[1].openState.body.items!![plan].duration!!,
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