package com.example.mycomposelearn.view.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycomposelearn.view.Routes
import com.example.mycomposelearn.viewmodel.AccountViewModel

@Composable
fun ScreenMain() {
    val navController = rememberNavController()
    val accountViewModel = hiltViewModel<AccountViewModel>()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(route = Routes.Login.route) {
            LoginPage(navController = navController, accountViewModel = accountViewModel)
        }

        composable(route = Routes.Home.route) {
            Home()
        }
    }
}