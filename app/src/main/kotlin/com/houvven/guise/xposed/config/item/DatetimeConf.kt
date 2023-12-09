package com.houvven.guise.xposed.config.item

import com.houvven.guise.xposed.config.ModuleConf
import kotlinx.serialization.Serializable

private val empty = DatetimeConf()

@Serializable
data class DatetimeConf(
    val datetime: Long? = null,
    val timezone: String? = null,
) : ModuleConf {
    override fun isEffective(): Boolean {
        return this != empty
    }
}
