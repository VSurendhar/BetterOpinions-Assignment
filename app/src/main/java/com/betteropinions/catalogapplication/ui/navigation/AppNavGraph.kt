package com.betteropinions.catalogapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.betteropinions.catalogapplication.R
import com.betteropinions.catalogapplication.ui.screens.enterNumberScreen.EnterNumberScreen
import com.betteropinions.catalogapplication.ui.screens.enterOtpScreen.EnterOtpScreen
import com.betteropinions.catalogapplication.ui.screens.mainScreen.MainScreen
import com.betteropinions.catalogapplication.ui.screens.onBoardScreen.BeforeAfterSlide
import com.betteropinions.catalogapplication.ui.screens.onBoardScreen.OnboardingScreen
import com.betteropinions.catalogapplication.ui.screens.splashScreen.SplashScreen


@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        modifier = modifier,
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(
                onSplashComplete = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(
                onOtpVerified = {
                    navController.navigate(Screen.EnterNumber.route)
                },
                slides = listOf(
                    BeforeAfterSlide(
                        R.drawable.img_onboard1_before,
                        R.drawable.img_onboard1_after
                    ),
                    BeforeAfterSlide(
                        R.drawable.img_onboard2_before,
                        R.drawable.img_onboard2_after
                    ),
                    BeforeAfterSlide(
                        R.drawable.img_onboard3_before,
                        R.drawable.img_onboard3_after
                    )
                )
            )
        }

        composable(route = Screen.EnterNumber.route) {
            EnterNumberScreen(
                onNumberSubmitted = { phoneNumber ->
                    navController.navigate(Screen.EnterOtp.createRoute(phoneNumber))
                }
            )
        }

        composable(
            route = Screen.EnterOtp.route,
            arguments = listOf(
                navArgument(Screen.EnterOtp.ARG_PHONE) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val phoneNumber = backStackEntry.arguments
                ?.getString(Screen.EnterOtp.ARG_PHONE)
                .orEmpty()

            EnterOtpScreen(
                phoneNumber = phoneNumber,
                onOtpVerified = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = Screen.Main.route) {
            MainScreen()
        }

    }

}