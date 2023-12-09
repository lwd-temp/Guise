package com.houvven.guise.xposed.store

import com.houvven.guise.xposed.config.ModuleConfiguration

object ModuleStoreHelper : ModuleStore {

    fun init() {
    }

    override fun get(packageName: String): ModuleConfiguration? {
        TODO("Not yet implemented")
    }

    override fun set(packageName: String, configuration: ModuleConfiguration) {
        TODO("Not yet implemented")
    }

    override fun isConfigured(packageName: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getConfiguredPackages(): List<String> {
        TODO("Not yet implemented")
    }

    fun setUseMediaStore(isUse: Boolean) {
        if (isUse) {

        }
    }
}