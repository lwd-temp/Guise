package com.houvven.guise.xposed.config.item

import com.houvven.guise.xposed.config.ModuleConf
import kotlinx.serialization.Serializable


@Serializable
data class AppInfoConf(
    val versionName: String? = null,
    val versionCode: Int? = null
) : ModuleConf {
    override fun isEffective(): Boolean {
        return this != AppInfoConf()
    }
}
