package com.betteropinions.catalogapplication.ui.screens.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.betteropinions.catalogapplication.R
import com.betteropinions.catalogapplication.ui.screens.mainScreen.navigation.MainNavGraph
import com.betteropinions.catalogapplication.ui.screens.mainScreen.navigation.MainScreen
import com.betteropinions.catalogapplication.ui.screens.onBoardScreen.SetWhiteStatusBar
import com.betteropinions.catalogapplication.ui.theme.Purple
import com.betteropinions.catalogapplication.ui.theme.SlateGrayBlue
import com.betteropinions.catalogapplication.ui.theme.catalogColors
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
    val colors = MaterialTheme.catalogColors
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    SetWhiteStatusBar()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple)
            .statusBarsPadding(),
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
                            selectedIconColor = colors.purple,
                            selectedTextColor = colors.purple,
                            indicatorColor = Transparent,
                            unselectedIconColor = SlateGrayBlue,
                            unselectedTextColor = Color(0xFFA0A6B1),
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        MainNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}