package com.houvven.guise.ui.screen.launch.pkgs

import com.houvven.guise.ui.mvi.Intent
import com.houvven.guise.ui.mvi.MviViewModel
import com.houvven.guise.utils.pkg.PkgScanner
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class PkgsViewModel @Inject constructor(
    private val pkgScanner: PkgScanner
) : MviViewModel() {

    override fun reduce(intent: Intent) {
        TODO("Not yet implemented")
    }
}
