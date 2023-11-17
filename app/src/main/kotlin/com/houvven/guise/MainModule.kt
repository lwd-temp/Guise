package com.houvven.guise

import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface
import io.github.libxposed.api.XposedModuleInterface.ModuleLoadedParam

private lateinit var module: XposedModule

class MainModule(private val base: XposedModule, private val param: ModuleLoadedParam) :
    XposedModule(base, param) {

    init {
        module = this
        module.log("module init at ${param.processName}")
    }

    override fun onPackageLoaded(param: XposedModuleInterface.PackageLoadedParam) {
        super.onPackageLoaded(param)
        if (!param.isFirstPackage) return
        log("start loading package ${param.packageName}")
    }
}