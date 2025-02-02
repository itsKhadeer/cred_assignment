package com.example.stack_frame_work

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// Individual stack item composable
@Composable
fun StackItem(
    item: StackItem,
    isVisible: Boolean,
    isExpanded: Boolean,
    isDragHandlerVisible: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(),
        exit = shrinkVertically(),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
        ) {

            // Show drag handler content if conditions are met, otherwise show collapsed content
            AnimatedVisibility(
                visible = !isExpanded && !isDragHandlerVisible,
                enter = expandVertically(),
                exit = shrinkVertically(),
                modifier = if (!isExpanded) Modifier.clickable { onToggle() } else Modifier
            ) {
                if (isDragHandlerVisible) {
                    item.dragHandlerContent()
                } else {
                    item.collapsedContent()
                }
            }

            AnimatedVisibility(
                visible = !isExpanded && isDragHandlerVisible,
                enter = expandVertically(),
                exit = shrinkVertically(),
                modifier = if (!isExpanded) Modifier.clickable { onToggle() } else Modifier
            ) {
                item.dragHandlerContent()
            }
            // Show expanded content with animation when expanded
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(),
                exit = shrinkVertically(),
            ) {
                item.content()
            }

        }
    }
}
