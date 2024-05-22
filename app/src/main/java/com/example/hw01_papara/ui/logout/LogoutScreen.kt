package com.example.hw01_papara.ui.logout

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hw01_papara.R
import com.example.hw01_papara.ui.bottombar.BottomBarScreen
import com.example.hw01_papara.viewmodel.AuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun LogoutScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {
    val context = LocalContext.current
    AlertDialog(
        icon = {
            Icon(painter = painterResource(id = R.drawable.ic_logout), contentDescription = "Example Icon")
        },
        title = {
            Text(text = "Exit?")
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
                    viewModel.logoutUser()
                    navController.navigate(BottomBarScreen.Login.route)
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