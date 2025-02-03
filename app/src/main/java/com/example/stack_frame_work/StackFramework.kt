package com.example.stack_frame_work

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun StackFramework(
    items: List<StackItem>,
    modifier: Modifier = Modifier,
    stackState: StackState,
    startCtaContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    require(items.size in 2..4) {
        "Stack framework must contain between 2 and 4 items. Current size: ${items.size}"
    }
    Column() {
        Box(
            modifier = modifier
                .weight(1f)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (stackState.expandedIndex == null) {
                        return@clickable
                    }

                    if (stackState.expandedIndex != 0) {
                        stackState.toggleExpanded(stackState.expandedIndex!!.minus(1))
                    } else if (stackState.expandedIndex!! == 0) {
                        stackState.toggleExpanded(null)
                    }

                },
            contentAlignment = Alignment.Center
        ) {
            content()
        }

        if(stackState.expandedIndex == null) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        stackState.toggleExpanded(0)
                    },
                contentAlignment = Alignment.Center
                ) {
                startCtaContent()
            }
        }
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items.forEachIndexed { index, item ->
                StackItem(
                    item = item,
                    isVisible = isVisible(stackState.expandedIndex, index),
                    isExpanded = isExpanded(stackState.expandedIndex, index),
                    onToggle = { stackState.toggleExpanded(index+it) },
                    modifier = modifier
                )
            }
        }
    }
}


fun isExpanded(currentIndex: Int?, index: Int): Boolean {
    return currentIndex == index
}

fun isVisible(currentIndex: Int?, index: Int): Boolean {
    if (currentIndex == null) {
        return false
    }
    if (currentIndex == 0 && index == 0) return true
    if (currentIndex >= index) {
        return true
    }
    return false
}
