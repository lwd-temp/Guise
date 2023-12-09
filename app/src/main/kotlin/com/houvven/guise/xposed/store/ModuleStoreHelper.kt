package com.houvven.guise.xposed.store

import android.content.Context
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.houvven.guise.xposed.config.ModuleConfiguration
import com.houvven.guise.xposed.store.impl.MediaStoreModuleStore
import com.houvven.guise.xposed.store.impl.SharedPreferenceModuleStore

object ModuleStoreHelper : ModuleStore {

    /**
     * store preferences name
     */
    private const val PREFS_NAME = "module_configurations"

    /**
     * media store name
     */
    private const val MEDIA_STORE_NAME = "guise_configurations.json"

    /**
     * store instance
     */
    private lateinit var store: ModuleStore

    /**
     * current store instance, only used on host app runtime
     */
    private lateinit var currentStore: ModuleStore

    @JvmOverloads
    fun init(context: Context? = null, packageParam: PackageParam?) {
        store = when {
            context != null -> {
                val useMediaStore = context.dataDir.resolve("media_store").exists()
                if (useMediaStore)
                    MediaStoreModuleStore(MEDIA_STORE_NAME)
                else
                    SharedPreferenceModuleStore(PREFS_NAME, context)
            }

            packageParam != null -> {
                SharedPreferenceModuleStore(PREFS_NAME, packageParam)
            }

            else -> throw RuntimeException(
                buildString {
                    append("You must initialize either context or packageParam before using this method.")
                    append("On the app run time, you must initialize context.")
                    append("On the xposed module run time, you must initialize packageParam.")
                }
            )
        }
    }


    /**
     * When using root-free xposed frameworks, such as TaiChi and Lspatch, you should use the media store to read and write configurations.
     * However, when reading the configuration, the host application cannot read the configuration of the module itself and cannot determine whether to use media store or shared preference.
     * Therefore, when reading the configuration, you need to read the (shared preference) first. If it is not read, then read the media store.
     * If the configuration is read in the media store, then the current store is set to the media store.
     * When the current store initialization is completed, the current store is used by default to read and write configurations, and it is no longer judged whether to use the media store.
     */
    override fun get(packageName: String): ModuleConfiguration {
        if (::currentStore.isInitialized) {
            return currentStore.get(packageName)
        }
        val medStore = MediaStoreModuleStore(MEDIA_STORE_NAME)
        val configurationSp = store.get(packageName)
        val configurationMs = medStore.get(packageName)
        return when {
            configurationSp.isEffective() -> {
                currentStore = store
                configurationSp
            }

            configurationMs.isEffective() -> {
                currentStore = medStore
                configurationMs
            }

            else -> ModuleConfiguration()
        }
    }


    override fun set(packageName: String, configuration: ModuleConfiguration) {
        store.set(packageName, configuration)
    }


    override fun isConfigured(packageName: String): Boolean {
        return store.isConfigured(packageName)
    }


    override fun getConfiguredPackages(): List<String> {
        return store.getConfiguredPackages()
    }
}