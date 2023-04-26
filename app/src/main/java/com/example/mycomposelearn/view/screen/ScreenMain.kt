package com.example.mycomposelearn.view.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycomposelearn.view.Routes
import com.example.mycomposelearn.viewmodel.LoginViewModel

@Composable
fun ScreenMain() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(route = Routes.Login.route) {
            LoginPage(navController = navController, loginViewModel = hiltViewModel())
        }

        composable(route = Routes.Home.route) {
            Home()
        }
    }
}