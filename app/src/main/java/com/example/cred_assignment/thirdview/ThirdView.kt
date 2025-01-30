package com.example.cred_assignment.thirdview

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
import com.example.cred_assignment.models.Content

@Composable
fun ThirdView(content: Content, changeBank: (Int) -> Unit, bank: Int) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.64f)
            .fillMaxWidth()
            .background(Color(0xFF171E27))
            .clip(
                shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)
            )
            .background(color = Color(0xFF1B232C)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 20.dp)
        ) {
            Text(
                text = content.items[2].openState.body.title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = content.items[2].openState.body.subtitle,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                color = Color.Gray.copy(0.5f)
            )
            Spacer(modifier = Modifier.height(20.dp))
            //chip
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            ) {
                content.items[2].openState.body.items?.let { banks ->
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
                                        text = banks[it].subtitle!!, color = Color.Gray.copy(0.5f)
                                    )
                                }
                            }
                            Checkbox(
                                it == bank,
                                onCheckedChange = { _ -> changeBank(it) },
                            )
                        }
                    }
                }
            }
            OutlinedButton(onClick = {}) {
                Text(text = content.items[2].openState.body.footer)
            }
        }
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = Color(0xFF37439C), contentColor = Color.White
            )
        ) {
            Text(text = content.items[2].ctaText)
        }
    }
}