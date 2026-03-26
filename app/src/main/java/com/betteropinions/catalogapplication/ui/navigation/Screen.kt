package com.betteropinions.catalogapplication.ui.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash")
    data object Onboarding : Screen("onboarding")
    data object EnterNumber : Screen("enter_number")
    data object EnterOtp : Screen("enter_otp/{phoneNumber}") {
        fun createRoute(phoneNumber: String) = "enter_otp/$phoneNumber"
        const val ARG_PHONE = "phoneNumber"
    }

    data object Main : Screen("main")

    data object Home : Screen("home")
    data object Create : Screen("create")
    data object Project : Screen("project")

}
