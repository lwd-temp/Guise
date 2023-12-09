package com.houvven.guise.xposed.store

import android.content.Context
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.houvven.guise.xposed.store.impl.SharedPreferenceModuleStore


internal object ModuleStoreSingleton {

    private const val PREFS_NAME = "module_configurations"
    private const val MEDIA_FILE_NAME = "module_configurations.json"

    private lateinit var store: ModuleStore

    // @Throws(IllegalStateException::class)
    // fun get(): ModuleStore {
    //     if (::store.isInitialized) return store
    //     throw IllegalStateException("can not get ModuleStore instance, please use init function first")
    // }

    @JvmOverloads
    @Throws(IllegalStateException::class)
    fun get(
        context: Context? = null,
        packageParam: PackageParam? = null,
    ): ModuleStore {
        if (::store.isInitialized) return store

        checkNotNull(context ?: packageParam) {
            "context or packageParam must not be null, if useMediaStoreType is false"
        }

        context?.let { c ->
            return SharedPreferenceModuleStore(PREFS_NAME, c).also { store = it }
        }
        packageParam?.let { param ->
            return SharedPreferenceModuleStore(PREFS_NAME, param).also { store = it }
        }

        throw IllegalStateException("can not get ModuleStore instance")
    }
}