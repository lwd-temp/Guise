package com.houvven.guise.xposed.store.impl

import android.content.Context
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.highcapable.yukihookapi.hook.xposed.prefs.YukiHookPrefsBridge
import com.houvven.guise.xposed.config.ModuleConfiguration
import com.houvven.guise.xposed.store.ModuleStore

class SharedPreferenceModuleStore : ModuleStore {

    private lateinit var context: Context
    private lateinit var packageParam: PackageParam
    private val prefsName: String
    private lateinit var prefs: YukiHookPrefsBridge

    @Suppress("unused")
    constructor(prefsName: String, context: Context) {
        this.context = context
        this.prefsName = prefsName
        init()
    }

    @Suppress("unused")
    constructor(prefsName: String, packageParam: PackageParam) {
        this.packageParam = packageParam
        this.prefsName = prefsName
        init()
    }

    private fun init() {
        prefs = if (this::context.isInitialized) {
            context.prefs(prefsName)
        } else if (this::packageParam.isInitialized) {
            packageParam.prefs(prefsName)
        } else {
            throw RuntimeException(
                buildString {
                    append("You must initialize either context or packageParam before using this method.")
                    append("On the app run time, you must initialize context.")
                    append("On the xposed module run time, you must initialize packageParam.")
                }
            )
        }
    }

    override fun get(packageName: String): ModuleConfiguration {
        val jsonStr = prefs.getString(packageName, "")
        if (jsonStr.isBlank()) {
            return ModuleConfiguration()
        }
        return ModuleConfiguration.fromJsonString(jsonStr)
    }

    override fun set(packageName: String, configuration: ModuleConfiguration) {
        if (!this::context.isInitialized) {
            throw RuntimeException("You must initialize context before using this method.")
        }
        val prefsBridge = context.prefs(prefsName)
        if (!configuration.isEffective()) {
            prefsBridge.edit { remove(packageName) }
            return
        }
        prefsBridge.edit { putString(packageName, configuration.toJsonString()) }
    }

    override fun isConfigured(packageName: String): Boolean {
        check(this::context.isInitialized) {
            "You must initialize context before using this method."
        }
        val prefsBridge = context.prefs(prefsName)
        return prefsBridge.contains(packageName)
    }

    override fun getConfiguredPackages(): List<String> {
        return prefs.all().map { it.key }
    }
}