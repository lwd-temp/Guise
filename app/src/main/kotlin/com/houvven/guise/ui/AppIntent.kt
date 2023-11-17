package com.houvven.guise.ui

import com.houvven.guise.ui.mvi.Intent
import com.houvven.guise.utils.pkg.PkgWithCompose

internal sealed class AppIntent : Intent {
    data class LoadPkgs(val apps: ArrayList<PkgWithCompose>) : AppIntent()
}