package com.houvven.guise.ui.screen.auth.signup

import androidx.navigation.NavHostController
import com.houvven.guise.mvi.Intent
import com.houvven.guise.mvi.MviViewModel
import com.houvven.guise.ui.Screen
import kotlinx.coroutines.flow.MutableStateFlow


data class SignupState(
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val visiblePassword: Boolean = false,
    val visibleConfirmPassword: Boolean = false,
)

sealed class SignupIntent : Intent {
    data class UsernameChange(val username: String) : SignupIntent()
    data class PasswordChange(val password: String) : SignupIntent()
    data class ConfirmPasswordChange(val confirmPassword: String) : SignupIntent()
    data object TogglePasswordVisibility : SignupIntent()
    data object ToggleConfirmPasswordVisibility : SignupIntent()
    data object HandleSignup : SignupIntent()
    data class ToLogin(val navController: NavHostController) : SignupIntent()
}


internal class SignupViewModel : MviViewModel() {
    private var _state = MutableStateFlow(SignupState())
    val state = _state

    override fun reduce(intent: Intent) {
        intent.run {
            when (this) {
                is SignupIntent.UsernameChange ->
                    _state.value = _state.value.copy(username = username)

                is SignupIntent.PasswordChange ->
                    _state.value = _state.value.copy(password = password)

                is SignupIntent.ConfirmPasswordChange ->
                    _state.value = _state.value.copy(confirmPassword = confirmPassword)

                is SignupIntent.TogglePasswordVisibility ->
                    _state.value =
                        _state.value.copy(visiblePassword = !_state.value.visiblePassword)

                is SignupIntent.ToggleConfirmPasswordVisibility ->
                    _state.value =
                        _state.value.copy(visibleConfirmPassword = !_state.value.visibleConfirmPassword)

                is SignupIntent.HandleSignup -> TODO()
                is SignupIntent.ToLogin -> handleToLogin(navController)
            }
        }
    }

    private fun handleToLogin(navController: NavHostController) {
        // TODO: Change this to the login screen
        navController.navigate(Screen.Launch.route)
    }

}