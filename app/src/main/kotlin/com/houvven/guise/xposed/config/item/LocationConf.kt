package com.houvven.guise.xposed.config.item

import com.houvven.guise.xposed.config.ModuleConf
import kotlinx.serialization.Serializable

@Serializable
data class LocationConf(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val altitude: Double? = null,

    /**
     * some app will use wifi connect info to locate your location
     */
    val prohibitWifiLocation: Boolean? = null,
    /**
     * some app will use cell info to locate your location
     */
    val prohibitCellLocation: Boolean? = null,

    // other config
    val wifiSSID: String? = null,
    val wifiBSSID: String? = null,
    val wifiMac: String? = null,
) : ModuleConf {

    override fun isEffective(): Boolean {
        return this != LocationConf()
    }

}