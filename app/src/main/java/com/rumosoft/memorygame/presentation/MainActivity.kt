package com.rumosoft.memorygame.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.rumosoft.library_components.presentation.theme.MemoryGameTheme
import com.rumosoft.memorygame.presentation.navigation.NavigationHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoryGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    MemoryApp()
                }
            }
        }
    }
}

@Composable
fun MemoryApp() {
    val navController = rememberNavController()

    NavigationHost(navController)
}
