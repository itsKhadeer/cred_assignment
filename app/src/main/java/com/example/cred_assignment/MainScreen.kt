package com.example.cred_assignment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cred_assignment.firstview.FirstView
import com.example.cred_assignment.models.Content
import com.example.cred_assignment.secondview.SecondView
import com.example.cred_assignment.secondview.SecondViewDragHandler
import com.example.cred_assignment.thirdview.ThirdView
import com.example.cred_assignment.thirdview.ThirdViewDragHandler
import com.example.cred_assignment.viewmodel.MainViewModel
import com.example.cred_assignment.viewmodel.UiState

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val content = (uiState as UiState.Success).content
            ShowContent(content)
        }

        is UiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                ErrorDisplay(message = (uiState as UiState.Error).message,
                    onRetry = { viewModel.fetchContent() })
            }
        }
    }

}

@Composable
fun ErrorDisplay(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error
        )
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowContent(content: Content) {
    var amount by remember { mutableIntStateOf(content.items[0].openState.body.card?.minRange!!) }
    var plan by remember { mutableIntStateOf(0) }
    var bank by remember { mutableIntStateOf(0) }

    val scope = rememberCoroutineScope()

    var showBottomSheet1 by remember { mutableStateOf(false) }
    val modelBottomSheetState1 = rememberModalBottomSheetState(true)

    var showBottomSheet2 by remember { mutableStateOf(false) }
    val modelBottomSheetState2 = rememberModalBottomSheetState(true)

    //assuming all the buttons are non functional other than the first view card
    Scaffold(modifier = Modifier.background(color = Color(0xFF171E27)),
        topBar = { CredTopAppBar() }) { paddingValues ->
        FirstView(paddingValues = paddingValues,
            content = content,
            { showBottomSheet1 = true },
            amount = amount,
            changeAmount = { change: Int -> amount = change })

        if (showBottomSheet1) {
            ModalBottomSheet(onDismissRequest = { showBottomSheet1 = false },
                sheetState = modelBottomSheetState1,
                dragHandle = {
                    SecondViewDragHandler(
                        scope = scope,
                        modelBottomSheetState1 = modelBottomSheetState1,
                        changeState = { showBottomSheet1 = false },
                        content = content,
                        amount = amount
                    )
                }) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.75f)
                        .fillMaxWidth()
                        .background(Color(0xFF12181F))
                        .clip(shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
                        .background(color = Color(0xFF171E27)),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SecondView(content = content,
                        changeSheet = { showBottomSheet2 = true },
                        plan = plan,
                        changePlan = { change -> plan = change })
                    if (showBottomSheet2) {
                        ModalBottomSheet(
                            dragHandle = {
                                ThirdViewDragHandler(
                                    scope = scope,
                                    changeState = { showBottomSheet2 = false },
                                    modelBottomSheetState2 = modelBottomSheetState2,
                                    showBottomSheet2 = showBottomSheet2,
                                    content = content,
                                    plan = plan
                                )
                            },
                            onDismissRequest = { showBottomSheet2 = false },
                            sheetState = modelBottomSheetState2,
                        ) {
                            ThirdView(
                                content = content,
                                changeBank = { change -> bank = change },
                                bank = bank
                            )
                        }
                    }
                }
            }
        }
    }
}