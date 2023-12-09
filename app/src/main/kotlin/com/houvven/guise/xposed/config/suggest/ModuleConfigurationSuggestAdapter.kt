package com.houvven.guise.xposed.config.suggest

interface ModuleConfigurationSuggestAdapter<T> {
    val label: String
    val value: T
}