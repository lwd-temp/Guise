package com.houvven.guise.xposed.hooker.impl

import com.houvven.guise.xposed.config.ModuleConfiguration
import com.houvven.guise.xposed.hooker.Hooker

class BatteryPercentHooker(config: ModuleConfiguration) : Hooker(config) {

    private val batteryPercent = config.batteryPercent

    override fun onHook() {

    }
}