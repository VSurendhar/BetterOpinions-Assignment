package com.betteropinions.catalogapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.betteropinions.catalogapplication.ui.navigation.AppNavGraph
import com.betteropinions.catalogapplication.ui.theme.CatalogApplicationTheme


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
