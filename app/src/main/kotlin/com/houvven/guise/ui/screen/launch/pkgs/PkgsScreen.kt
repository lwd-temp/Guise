package com.houvven.guise.ui.screen.launch.pkgs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.houvven.guise.ui.AppViewModel

@Composable
internal fun PkgsScreen() {
    val viewModel = hiltViewModel<AppViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isLoadingPkgs) {
        LoadProgressIndicator(state)
        return
    }

    Scaffold { pv ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = pv.calculateTopPadding())
        ) {
            LazyColumn(
                contentPadding = PaddingValues(14.dp)
            ) {
                items(state.pkgs) {
                    key(it.packageName) {
                        PkgCard(
                            appName = it.name,
                            packageName = it.packageName,
                            icon = { it.icon }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}