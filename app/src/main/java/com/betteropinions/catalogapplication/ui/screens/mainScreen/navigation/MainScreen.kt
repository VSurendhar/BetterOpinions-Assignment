package com.betteropinions.catalogapplication.ui.screens.mainScreen.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.betteropinions.catalogapplication.ui.screens.mainScreen.components.CreateTab
import com.betteropinions.catalogapplication.ui.screens.mainScreen.components.HomeTab
import com.betteropinions.catalogapplication.ui.screens.mainScreen.components.ProjectsTab


sealed class MainScreen(val route: String) {
    object Home : MainScreen("main_home")
    object Create : MainScreen("main_create")
    object Projects : MainScreen("main_projects")
}

@Composable
fun MainNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = MainScreen.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        composable(MainScreen.Home.route) {
            HomeTab(modifier)
        }
        composable(MainScreen.Create.route) {
            CreateTab(modifier)
        }
        composable(MainScreen.Projects.route) {
            ProjectsTab(modifier)
        }
    }
}