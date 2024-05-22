package com.example.hw01_papara

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hw01_papara.presentation.detail.DetailScreen
import com.example.hw01_papara.ui.bottombar.BottomBarScreen
import com.example.hw01_papara.ui.bottombar.BottomNav
import com.example.hw01_papara.ui.chatscreen.MessagingScreen
import com.example.hw01_papara.ui.loginscreen.LoginScreen
import com.example.hw01_papara.ui.registerscreen.RegisterScreen
import com.example.hw01_papara.ui.splashscreen.SplashScreen
import com.example.hw01_papara.ui.theme.Hw01paparaTheme
import com.example.hw01_papara.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw01paparaTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainScreen() {
        Scaffold(
            bottomBar = { BottomNav() },
        ) {
        }
    }

    @Composable
    fun SetupNavGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = "splash_screen",
        ) {
            composable("splash_screen") {
                SplashScreen(navController = navController)
            }
            composable("login_screen") {
                LoginScreen(navController = navController)
            }
            composable("register_screen") {
                RegisterScreen(navController = navController)
            }
            composable("main_screen") {
                MainScreen()
            }

        }
    }

}

