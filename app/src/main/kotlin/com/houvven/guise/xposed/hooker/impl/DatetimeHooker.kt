package com.houvven.guise.xposed.hooker.impl

import android.icu.util.TimeZone
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.type.java.TimeZoneClass
import com.houvven.guise.xposed.config.ModuleConfiguration
import com.houvven.guise.xposed.config.item.DatetimeConf
import com.houvven.guise.xposed.hooker.Hooker

class DatetimeHooker(config: ModuleConfiguration) : Hooker(config) {

    private val conf = config.datetimeConf ?: DatetimeConf()

    override fun onHook() {
        if (!conf.isEffective()) return
        this.hookOfTimeZone()
    }


    private fun hookOfTimeZone() {
        if (conf.timezone == null) return
        arrayOf(TimeZone::class.java, TimeZoneClass).forEach {
            it.method { name = "getDefault" }
                .hook()
                .before {
                    result = conf.timezone
                }
        }
    }
}