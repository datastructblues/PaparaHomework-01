package com.example.hw01_papara

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hw01_papara.navigation.NavigationGraph
import com.example.hw01_papara.ui.theme.Hw01paparaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hw01paparaTheme {
                navController = rememberNavController()
                NavigationGraph(navController = navController  )
            }
        }
    }
}
