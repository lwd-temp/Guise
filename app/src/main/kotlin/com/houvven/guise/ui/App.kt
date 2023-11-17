package com.houvven.guise.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.houvven.guise.ui.screen.auth.login.LoginScreen
import com.houvven.guise.ui.screen.auth.signup.SignupScreen
import com.houvven.guise.ui.screen.func.FuncScreen
import com.houvven.guise.ui.screen.launch.LaunchScreen
import com.houvven.guise.ui.theme.AppTheme


internal sealed class Screen(val route: String) {
    data object Launch : Screen("Launch")
    data object Login : Screen("Login")
    data object Signup : Screen("Signup")
    data object Func : Screen("Func")
}

internal val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No nav controller provided")
}

@Composable
fun App() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CompositionLocalProvider(LocalNavController provides rememberNavController()) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(
                        navController = LocalNavController.current,
                        startDestination = Screen.Launch.route
                    ) {
                        composable(Screen.Launch.route) { LaunchScreen() }
                        composable(Screen.Login.route) { LoginScreen() }
                        composable(Screen.Signup.route) { SignupScreen() }
                        composable(Screen.Func.route) { FuncScreen() }
                    }
                }
            }
        }
    }
}