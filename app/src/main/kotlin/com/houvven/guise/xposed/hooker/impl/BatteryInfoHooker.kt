package com.houvven.guise.xposed.hooker.impl

import android.os.BatteryManager
import com.houvven.guise.xposed.config.ModuleConfiguration
import com.houvven.guise.xposed.hooker.Hooker

class BatteryInfoHooker(config: ModuleConfiguration) : Hooker(config) {

    private val batteryPercent = config.batteryPercent

    override fun onHook() {
        if (batteryPercent == null) return
        BatteryManager::class.java

    }
}