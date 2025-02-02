package com.example.cred_assignment.presentation.thirdview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cred_assignment.R
import com.example.cred_assignment.domain.models.ThirdViewContent

@Composable
fun ThirdView(content: ThirdViewContent, changeBank: (Int) -> Unit, bank: Int) {
    Column(
        modifier = Modifier
            .background(Color.LightGray.copy(alpha = 0.9f))
            .fillMaxHeight(0.64f)
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
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
                color = Color.Black.copy(0.5f)
            )
            Spacer(modifier = Modifier.height(20.dp))
            //chip
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            ) {
                content.openState.items.let { banks ->
                    items(banks.size) { it ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                    contentDescription = "bank",
                                    modifier = Modifier.size(70.dp)
                                )
                                Spacer(Modifier.width(20.dp))
                                Column(verticalArrangement = Arrangement.Center) {
                                    Text(text = banks[it].title)
                                    Text(
                                        text = banks[it].subtitle, color = Color.Black.copy(0.5f)
                                    )
                                }
                            }
                            Checkbox(
                                it == bank,
                                onCheckedChange = { _ -> changeBank(it) },
                                colors = CheckboxDefaults.colors().copy(
                                    checkedCheckmarkColor = Color.Black,
                                    checkedBoxColor = Color.White,
                                    checkedBorderColor = Color.Gray,
                                    uncheckedBorderColor = Color.Black,
                                )
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            OutlinedButton(
                onClick = {},
                colors = ButtonDefaults.outlinedButtonColors().copy(
                    contentColor = Color.Black,
                    containerColor = Color.White
                )
            ) {
                Text(text = content.openState.footer)
            }
        }
    }
}