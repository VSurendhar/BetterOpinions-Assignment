package com.betteropinions.catalogapplication

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.betteropinions.catalogapplication.ui.navigation.AppNavGraph
import com.betteropinions.catalogapplication.ui.theme.CatalogApplicationTheme
import com.betteropinions.catalogapplication.ui.theme.Purple


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatalogApplicationTheme {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}
