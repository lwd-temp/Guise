package com.houvven.guise.ui.screen.auth.login

import android.util.Log
import androidx.navigation.NavHostController
import com.houvven.guise.mvi.Intent
import com.houvven.guise.mvi.MviViewModel
import com.houvven.guise.ui.Screen
import kotlinx.coroutines.flow.MutableStateFlow

internal data class LoginState(
    val username: String = "",
    val password: String = "",
    val visiblePassword: Boolean = false,
)

internal sealed class LoginIntent : Intent {
    data class UsernameChange(val username: String) : LoginIntent()
    data class PasswordChange(val password: String) : LoginIntent()
    data object Login : LoginIntent()
    data object ToForgetPassword : LoginIntent()
    data object TogglePasswordVisibility : LoginIntent()
    data class ToSignup(var navController: NavHostController) : LoginIntent()
}


internal class LoginViewModel : MviViewModel() {
    private var _state = MutableStateFlow(LoginState())
    val state = _state

    override fun reduce(intent: Intent) {
        when (intent) {
            is LoginIntent.UsernameChange ->
                _state.value = _state.value.copy(username = intent.username)

            is LoginIntent.PasswordChange ->
                _state.value = _state.value.copy(password = intent.password)

            is LoginIntent.TogglePasswordVisibility ->
                _state.value = _state.value.copy(visiblePassword = !_state.value.visiblePassword)

            is LoginIntent.Login -> handleLogin()
            is LoginIntent.ToForgetPassword -> TODO()
            is LoginIntent.ToSignup -> handleToSignup(intent.navController)
        }
    }

    private fun handleLogin() {
        Log.d("LoginViewModel", "Handling login")
        // TODO: implement login
    }

    private fun handleToSignup(navController: NavHostController) {
        navController.navigate(Screen.Signup.route)
    }
}