package com.example.hw01_papara.ui.chatnavigate

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChatNavigaitonScreen(navController: NavController) {
    //navhostları birleştirebilirsem bu sayfa kullanılabilir olacak
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Hoşgeldiniz",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Bu uygulamada size yardımcı olacağız",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { navController.navigate("messaging_screen") }) {
                Text(text = "Chat'e Başla!")
            }
        }
    }
}
