package com.houvven.guise.xposed.hooker.impl

import com.houvven.guise.xposed.config.ModuleConfiguration
import com.houvven.guise.xposed.config.item.SimConf
import com.houvven.guise.xposed.hooker.Hooker

class SimHooker(config: ModuleConfiguration) : Hooker(config) {

    private val conf = config.simConf ?: SimConf()

    override fun onHook() {
        if (!conf.isEffective()) return
        this.hookSimOperator()
        this.hookSimOperatorName()
        this.hookSimCountryIso()
    }


    private fun hookSimOperator() {

    }

    private fun hookSimOperatorName() {

    }

    private fun hookSimCountryIso() {

    }
}