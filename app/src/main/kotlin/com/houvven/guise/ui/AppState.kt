package com.houvven.guise.ui

import com.houvven.guise.pkg.PkgWithCompose

data class AppState(
    val isLoadingPkgs: Boolean = false,
    val pkgs: ArrayList<PkgWithCompose> = arrayListOf(),
    val pkgQuantity: Int = 0,
    val loadedPkgQuantity: Int = 0
)
