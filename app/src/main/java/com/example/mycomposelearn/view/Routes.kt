package com.example.mycomposelearn.view

sealed class Routes(val route: String) {
    object Login: Routes("Login")
    object Home: Routes("HomePage")
}
