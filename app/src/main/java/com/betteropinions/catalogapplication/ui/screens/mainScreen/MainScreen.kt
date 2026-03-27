package com.betteropinions.catalogapplication.ui.screens.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.betteropinions.catalogapplication.R
import com.betteropinions.catalogapplication.ui.screens.mainScreen.navigation.MainNavGraph
import com.betteropinions.catalogapplication.ui.screens.mainScreen.navigation.MainScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

data class BottomNavItem(
    val label: String,
    val screen: MainScreen,
    val iconRes: Int,
)

val bottomNavItems = listOf(
    BottomNavItem("Home", MainScreen.Home, R.drawable.ic_home),
    BottomNavItem("Create", MainScreen.Create, R.drawable.ic_pencil),
    BottomNavItem("Projects", MainScreen.Projects, R.drawable.ic_project),
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFF5C2D91), // your purple
            darkIcons = false // white icons
        )
    }

    Scaffold(
        containerColor = Color(0xFF5C2D91),
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
            ) {
                bottomNavItems.forEach { item ->
                    val selected = currentDestination
                        ?.hierarchy
                        ?.any { it.route == item.screen.route } == true

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconRes),
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF5C2D91),
                            selectedTextColor = Color(0xFF5C2D91),
                            indicatorColor = Color(0xFFF3E8FF),
                            unselectedIconColor = Color(0xFF9E9E9E),
                            unselectedTextColor = Color(0xFF9E9E9E),
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        MainNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)  // add modifier param to MainNavGraph
        )
    }
}