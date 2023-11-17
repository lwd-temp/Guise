package com.houvven.guise.ui.screen.launch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.houvven.guise.ui.screen.launch.pkgs.PkgsScreen
import com.houvven.guise.ui.screen.launch.home.HomeScreen

internal sealed class LaunchView(val route: String, val icon: ImageVector) {
    data object Home : LaunchView("Home", Icons.Outlined.Home)
    data object Apps : LaunchView("Apps", Icons.Outlined.ListAlt)
}

private val launchViews = listOf(
    LaunchView.Home,
    LaunchView.Apps
)

@Composable
fun LaunchScreen() {
    val viewModel = hiltViewModel<LaunchViewModel>()
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            LaunchNavigationBar(viewModel, navController)
        }
    ) {
        Column(
            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
        ) {
            NavHost(navController = navController, startDestination = LaunchView.Home.route) {
                composable(LaunchView.Home.route) { HomeScreen() }
                composable(LaunchView.Apps.route) { PkgsScreen() }
            }
        }
    }
}


@Composable
private fun LaunchNavigationBar(
    viewModel: LaunchViewModel,
    navController: NavHostController,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Surface(shadowElevation = 8.dp) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            launchViews.forEach {
                val selected = it == state.currentView
                val iconButtonColors =
                    if (selected)
                        IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        )
                    else IconButtonDefaults.iconButtonColors()
                IconButton(
                    onClick = {
                        viewModel.sendIntent(
                            LaunchIntent.JumpTo(navController = navController, targetView = it)
                        )
                    },
                    colors = iconButtonColors
                ) {

                    val iconColor =
                        if (selected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp),
                        tint = iconColor
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    LaunchScreen()
}

