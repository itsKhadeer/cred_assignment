package com.example.cred_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.example.cred_assignment.domain.models.ThirdViewContent
import com.example.cred_assignment.presentation.MainScreen
import com.example.cred_assignment.presentation.ui.theme.Cred_assignmentTheme
import io.ktor.util.toUpperCasePreservingASCIIRules

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Cred_assignmentTheme {
                MainScreen()
            }
        }
    }
}



@Composable
fun FourthViewDragHandler(
    content: ThirdViewContent,
    bank: Int
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.LightGray.copy(alpha = 0.9f))
            .clip(
                RoundedCornerShape(
                    30.dp, 30.dp, 0.dp, 0.dp
                )
            )
            .background(Color.Gray.copy(alpha = 0.3f))

            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(0.7f)

        ) {
            content.openState.items[bank].title.toUpperCasePreservingASCIIRules()
                .let {
                    Text(
                        text = it
                    )
                }
            content.openState.items[bank].subtitle.toUpperCasePreservingASCIIRules()
                .let {
                    Text(
                        text = it,
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