package com.houvven.guise.ui

import androidx.lifecycle.viewModelScope
import com.houvven.guise.ui.mvi.Intent
import com.houvven.guise.ui.mvi.MviViewModel
import com.houvven.guise.utils.pkg.PkgScanner
import com.houvven.guise.utils.pkg.PkgWithCompose
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private var cachePkgs = arrayListOf<PkgWithCompose>()

@HiltViewModel
class AppViewModel @Inject constructor(
    private val pkgScanner: PkgScanner
) : MviViewModel() {
    private val _state = MutableStateFlow(AppState())
    val state = _state

    init {
        Timber.d("init")
        initialize()
    }

    private fun initialize() {
        if (cachePkgs.isNotEmpty()) {
            Timber.d("cached pkgs found")
            _state.value = _state.value.copy(isLoadingPkgs = false, pkgs = cachePkgs)
            return
        }
        loadPkgs()
    }

    override fun reduce(intent: Intent) {
        when (intent) {
            is AppIntent.LoadPkgs -> loadPkgs()
        }
    }

    private fun loadPkgs() {
        _state.value = _state.value.copy(isLoadingPkgs = true, pkgQuantity = pkgScanner.getPkgQuantity())
        viewModelScope.launch {
            val pkgs = PkgWithCompose.withPkg(pkgScanner.suspendScan {
                _state.value =
                    _state.value.copy(loadedPkgQuantity = _state.value.loadedPkgQuantity + 1)
            })
            cachePkgs = pkgs as ArrayList<PkgWithCompose>
            _state.value = _state.value.copy(isLoadingPkgs = false, pkgs = pkgs)
        }
    }
}
