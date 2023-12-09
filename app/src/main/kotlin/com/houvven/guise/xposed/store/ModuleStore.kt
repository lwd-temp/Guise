package com.houvven.guise.xposed.store

import com.houvven.guise.xposed.config.ModuleConfiguration


interface ModuleStore {
    fun get(packageName: String): ModuleConfiguration?
    fun set(packageName: String, configuration: ModuleConfiguration)
    fun isConfigured(packageName: String): Boolean

    /**
     * This method should only be called in the app running environment
     * and should not be called in the xposed module running environment.
     */
    fun getConfiguredPackages(): List<String>
}