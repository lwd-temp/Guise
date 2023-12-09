package com.houvven.guise.xposed.hooker.impl

import com.houvven.guise.xposed.config.ModuleConfiguration
import com.houvven.guise.xposed.config.item.IdentifierConf
import com.houvven.guise.xposed.hooker.Hooker

@Suppress("unused")
class IdentifierHooker(config: ModuleConfiguration) : Hooker(config) {

    private val conf = config.identifierConf ?: IdentifierConf()

    override fun onHook() {
        if (!conf.isEffective()) return
    }

    private fun hookAndroidID() {
        if (conf.androidId == null) return
    }

}