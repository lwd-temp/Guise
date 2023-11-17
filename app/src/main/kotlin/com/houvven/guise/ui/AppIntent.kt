package com.houvven.guise.ui

import com.houvven.guise.mvi.Intent
import com.houvven.guise.pkg.PkgWithCompose

internal sealed class AppIntent : Intent {
    data class LoadPkgs(val apps: ArrayList<PkgWithCompose>) : AppIntent()
}