package com.houvven.guise.xposed.config.item

import com.houvven.guise.xposed.config.ModuleConf

private val emptySimConf = SimConf()

data class SimConf(
    /**
     * sim operator, such as: 46000, 46001, 46002, 46003, etc.
     */
    val simOperator: String? = null,
    /**
     * sim operator name, such as: 中国移动, 中国联通, 中国电信, etc.
     */
    val simOperatorName: String? = null,
    /**
     * sim country iso, such as: cn, us, etc.
     */
    val simCountryIso: String? = null,
    /**
     * sim serial number, this value is unique and can be used to determine if it is the same card
     */
    val simSerialNumber: String? = null,
    /**
     * sim state, such as: 0, 1, 2, 3, 4, etc.
     */
    val simState: Int? = null,
) : ModuleConf {
    override fun isEffective(): Boolean {
        return this != emptySimConf
    }
}