package com.example.hw01_papara.ui.chatnavigate

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hw01_papara.R
import com.example.hw01_papara.ui.bottombar.BottomBarScreen
import com.example.hw01_papara.ui.theme.LightColor2
import com.example.hw01_papara.ui.theme.LightColor3
import com.example.hw01_papara.ui.theme.LightColor4
import com.example.hw01_papara.ui.theme.LightColor5

@Composable
fun ChatNavigationScreen(navController: NavController) {
    val backgroundPainter: Painter = painterResource(id = R.drawable.foddo)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = backgroundPainter,
                contentScale = ContentScale.Crop
            ),
        color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Welcome to\n\nGurme Chat!",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = LightColor3,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "In this app, we aim to answer all your cooking questions!",
                fontSize = 18.sp,
                color = LightColor4,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate(BottomBarScreen.Message.route) },
                colors = ButtonDefaults.buttonColors(LightColor2)
            ) {
                Text(
                    text = "Start Chat!",
                    color = LightColor5
                )
            }
        }
    }
}
