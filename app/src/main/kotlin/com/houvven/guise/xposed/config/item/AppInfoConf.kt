package com.houvven.guise.xposed.config.item

import com.houvven.guise.xposed.config.ModuleConf
import kotlinx.serialization.Serializable


private val empty = AppInfoConf()

@Serializable
data class AppInfoConf(
    val versionName: String? = null,
    val versionCode: Int? = null
) : ModuleConf {
    override fun isEffective(): Boolean {
        return this != empty
    }
}
