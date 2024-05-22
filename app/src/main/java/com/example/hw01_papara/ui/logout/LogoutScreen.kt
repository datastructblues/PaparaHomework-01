package com.example.hw01_papara.ui.logout

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.hw01_papara.R
import com.example.hw01_papara.ui.bottombar.BottomBarScreen

@Composable
fun LogoutScreen(navController: NavController) {
    AlertDialog(
        icon = {
            Icon(painter = painterResource(id = R.drawable.ic_logout), contentDescription = "Example Icon")
        },
        title = {
            Text(text = "Çıkış yapılsın mı?")
        },
        text = {
            Text(text = "Çıkış yapmak istediğinize emin misiniz?")
        },
        onDismissRequest = {
            navController.navigate(BottomBarScreen.Chat.route)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    //logout işlemi
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    navController.navigate(BottomBarScreen.Chat.route)
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}