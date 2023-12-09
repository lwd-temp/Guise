package com.houvven.guise.xposed.hooker.impl

import android.os.Build
import com.highcapable.yukihookapi.hook.core.YukiMemberHookCreator
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.type.android.BuildClass
import com.houvven.guise.xposed.config.ModuleConfiguration
import com.houvven.guise.xposed.hooker.Hooker
import com.houvven.guise.xposed.hooker.impl.PropertiesHooker.Companion.BuildPropHookType.NORMAL
import com.houvven.guise.xposed.hooker.impl.PropertiesHooker.Companion.BuildPropHookType.VERSION
import com.houvven.guise.xposed.hooker.impl.PropertiesHooker.Companion.BuildPropHookType.VERSION_CODES

/**
 * hook android system properties value, such as: ro.product.brand, ro.product.model, etc.
 *
 * include [Build] class 's field and [SystemProperties] class 's method.
 */
class PropertiesHooker(config: ModuleConfiguration) : Hooker(config) {

    companion object {
        /**
         * Build class 's field type, such as: Build, Build.VERSION, Build.VERSION_CODES
         */
        private enum class BuildPropHookType {
            /**
             * @see Build
             */
            NORMAL,

            /**
             * @see Build.VERSION
             */
            VERSION,

            /**
             * @see Build.VERSION_CODES
             */
            VERSION_CODES
        }

        /**
         * [PropertiesHooker] 's hook options.
         *
         * @param value the value of the set
         * @param fieldName build class 's field name
         * @param systemPropKey system properties key
         * @param type field type of the build class， such as: Build, Build.VERSION, Build.VERSION_CODES
         */
        private data class PropertiesHookOptions(
            val value: Any?,
            val fieldName: List<String> = emptyList(),
            val systemPropKey: List<String> = emptyList(),
            val type: BuildPropHookType = NORMAL
        )
    }


    private lateinit var hookOptions: List<PropertiesHookOptions>

    init {
        config.propertiesConf?.run {
            hookOptions = listOf(
                PropertiesHookOptions(
                    value = brand,
                    fieldName = listOf("BRAND", "MANUFACTURER"),
                    // systemPropKey = listOf("ro.product.brand", "ro.product.manufacturer")
                ),
                PropertiesHookOptions(
                    value = model,
                    fieldName = listOf("MODEL"),
                    // systemPropKey = listOf("ro.product.model")
                ),
                PropertiesHookOptions(
                    value = product,
                    fieldName = listOf("PRODUCT"),
                    // systemPropKey = listOf("ro.product.name")
                ),
                PropertiesHookOptions(
                    value = device,
                    fieldName = listOf("DEVICE"),
                    // systemPropKey = listOf("ro.product.device")
                ),
                PropertiesHookOptions(
                    value = characteristics,
                    systemPropKey = listOf("ro.build.characteristics")
                ),
                PropertiesHookOptions(
                    value = sdk,
                    fieldName = listOf("SDK_INT"),
                    // systemPropKey = listOf("ro.build.version.sdk"),
                    type = VERSION
                ),
                PropertiesHookOptions(
                    value = release,
                    fieldName = listOf("RELEASE"),
                    // systemPropKey = listOf("ro.build.version.release"),
                    type = VERSION
                ),
                PropertiesHookOptions(
                    value = fingerprint,
                    fieldName = listOf("FINGERPRINT")
                )
            ).filter {
                it.value != null
            }
        }
    }


    override fun onHook() {
        if (::hookOptions.isInitialized) {
            hookOptions.forEach { doHook(it) }
        }

        config.propertiesConf?.run {
            // hook fingerprint method
            fingerprint?.let {
                BuildClass
                    .method { name = "deriveFingerprint" }
                    .hook().before {
                        result = it
                    }
            }
        }
    }


    private fun doHook(options: PropertiesHookOptions) {
        if (options.value == null) return
        options.fieldName.forEach { fieldName ->
            setBuildPropFieldValue(fieldName, options.value, options.type)
        }

        options.systemPropKey.forEach {
            setSystemProp(it, options.value)
        }
    }

    /**
     * set build class 's static field value.
     * @param fieldName field name
     * @param value the value of the set
     * @param type field type of the build class， such as: Build, Build.VERSION, Build.VERSION_CODES
     */
    private fun setBuildPropFieldValue(
        fieldName: String,
        value: Any,
        type: BuildPropHookType
    ) {
        when (type) {
            NORMAL -> BuildClass
            VERSION -> Build.VERSION::class.java
            VERSION_CODES -> Build.VERSION_CODES::class.java
        }
            .field {
                name(fieldName)
                modifiers { isStatic }
            }
            .get(instance = null)
            .set(value)
    }


    private fun setSystemProp(
        name: String,
        value: Any
    ): YukiMemberHookCreator.MemberHookCreator.Result {
        return "android.os.SystemProperties"
            .toClass()
            .method { name("get") }
            .giveAll()
            .hookAll {
                before {
                    val arg = args.find { it == name } as String?
                    if (!arg.isNullOrBlank()) {
                        YLog.debug(tag = "PropertiesHook", msg = arg)
                        result = value
                    }
                }
            }
    }

}