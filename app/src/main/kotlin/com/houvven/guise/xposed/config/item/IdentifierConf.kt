package com.houvven.guise.xposed.config.item

import com.houvven.guise.xposed.config.ModuleConf
import kotlinx.serialization.Serializable

private val emptyUniqueIdentifierConf = IdentifierConf()

@Serializable
data class IdentifierConf(
    /**
     * A 64-bit random number that is generated when the device is first set up,
     * and remains unchanged throughout the device's lifetime.
     */
    val androidId: String? = null,
    /**
     * IMEI, International Mobile Equipment Identity.
     * Only for mobile phone. On new android version, user app was ban get this value.
     * For two SIM card phones, there are two IMEIs. such as: 866250047519999, 866250047519997
     */
    val imei: String? = null
) : ModuleConf {
    override fun isEffective(): Boolean {
        return this != emptyUniqueIdentifierConf
    }
}