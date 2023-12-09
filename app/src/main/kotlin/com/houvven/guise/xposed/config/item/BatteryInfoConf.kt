package com.houvven.guise.xposed.config.item

import com.houvven.guise.xposed.config.ModuleConf
import kotlinx.serialization.Serializable

@Serializable
data class BatteryInfoConf(
    val batteryPercent: Int? = null,
    val batteryHealth: Int? = null,
    val batteryStatus: Int? = null,
    val batteryTemperature: Int? = null
) : ModuleConf {
    override fun isEffective(): Boolean {
        return this != BatteryInfoConf()
    }
}

