package com.houvven.guise.xposed.config

sealed interface ModuleConfigurationConstant {

    enum class TimeHookMode : ModuleConfigurationConstant {
        ONCE,
        EVERY_TIME
    }
}