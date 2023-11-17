package com.houvven.guise.ui.screen.launch

import androidx.navigation.NavHostController
import com.houvven.guise.mvi.Intent
import com.houvven.guise.mvi.MviViewModel
import kotlinx.coroutines.flow.MutableStateFlow


internal data class LaunchState(
    val currentView: LaunchView = LaunchView.Home,
    val isBottomBarVisible: Boolean = true,
)

internal sealed class LaunchIntent : Intent {
    data class JumpTo(
        val navController: NavHostController,
        val targetView: LaunchView
    ) : LaunchIntent()
}

internal class LaunchViewModel : MviViewModel() {
    private val _state = MutableStateFlow(LaunchState())
    val state = _state

    override fun reduce(intent: Intent) {
        when (intent) {
            is LaunchIntent.JumpTo -> jumpTo(intent.navController, intent.targetView)
        }
    }

    private fun jumpTo(navController: NavHostController, targetView: LaunchView) {
        if (targetView == _state.value.currentView) return
        navController.navigate(targetView.route)
        _state.value = _state.value.copy(currentView = targetView)
    }
}