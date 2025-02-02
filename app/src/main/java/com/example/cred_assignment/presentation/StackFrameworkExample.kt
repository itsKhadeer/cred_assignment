package com.example.cred_assignment.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cred_assignment.FourthViewDragHandler
import com.example.cred_assignment.domain.models.ContentModel
import com.example.cred_assignment.presentation.firstview.FirstView
import com.example.cred_assignment.presentation.secondview.SecondView
import com.example.cred_assignment.presentation.secondview.SecondViewDragHandler
import com.example.stack_frame_work.StackFramework
import com.example.stack_frame_work.StackItem
import com.example.cred_assignment.presentation.thirdview.ThirdView
import com.example.cred_assignment.presentation.thirdview.ThirdViewDragHandler
import org.koin.androidx.compose.koinViewModel


@Composable
fun StackFrameworkExample(content: ContentModel, viewModel: CredViewModel = koinViewModel()) {
    val amount by viewModel.amount.collectAsState()
    val plan by viewModel.plan.collectAsState()
    val bank by viewModel.bank.collectAsState()
    val stackState by viewModel.stackState.collectAsState()
    val context = LocalContext.current
    val items =
        listOf(
            StackItem(
                id = 1,
                collapsedContent = {
                    //Borrow money (not present in api)
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Borrow Money",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .clip(RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp))
                            .background(Color(0xFF37439C))
                            .padding(20.dp),
                        color = Color.White
                    )
                },
                //dialer content
                content = {
                    FirstView(
                        PaddingValues(0.dp),
                        content.firstViewContent,
                        viewModel.changeAmount,
                        amount
                    )
                },
                dragHandlerContent = {
                    //Credit amount  RS XXX
                    SecondViewDragHandler(
                        key1 = content.firstViewContent.closedState.key1,
                        amount = amount,
                    )
                }
            ),
            StackItem(
                id = 2,
                collapsedContent = {
                    //Proceed to EMI Selection
                    Text(
                        textAlign = TextAlign.Center,
                        text = content.firstViewContent.ctaText,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .clip(RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp))
                            .background(Color(0xFF37439C))
                            .padding(20.dp),
                        color = Color.White
                    )
                },
                content = {
                    //card content
                    SecondView(
                        content = content.secondViewContent,
                        plan = plan,
                        changePlan = viewModel.changePlan
                    )
                },
                dragHandlerContent = {
                    //shows EMI Plan selected
                    ThirdViewDragHandler(
                        content.secondViewContent,
                        plan
                    )
                }
            ),
            StackItem(
                id = 3,
                collapsedContent = {
                    //select your bank account
                    Text(
                        textAlign = TextAlign.Center,
                        text = content.secondViewContent.ctaText,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .background(Color.LightGray.copy(0.5f))
                            .clip(RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp))
                            .background(Color(0xFF37439C))
                            .padding(20.dp),
                        color = Color.White
                    )
                },
                content = {
                    // bank selection stack
                    ThirdView(
                        content = content.thirdViewContent,
                        bank = bank,
                        changeBank = viewModel.changeBank
                    )
                },
                dragHandlerContent = {
                    //shows selected bank
                    FourthViewDragHandler(
                        content.thirdViewContent,
                        bank
                    )
                }
            ),
            StackItem(
                id = 4,
                collapsedContent = {
                    //tap for 1 click kyc
                    Text(
                        textAlign = TextAlign.Center,
                        text = content.thirdViewContent.cta,
                        modifier = Modifier
                            .clickable {
                                val url = "https://cred.club/"
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            }
                            .fillMaxWidth()
                            .height(65.dp)
                            .background(Color.LightGray.copy(0.9f))
                            .clip(RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp))
                            .background(Color(0xFF37439C))
                            .padding(20.dp),
                        color = Color.White
                    )
                },
                content = {},
                dragHandlerContent = {}
            )
        )

    StackFramework(items = items, stackState = stackState) {
        val scrollState = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .scrollable(scrollState, Orientation.Vertical)
        ) {

            Text(text = "Stack Framework")

        }
    }
}
