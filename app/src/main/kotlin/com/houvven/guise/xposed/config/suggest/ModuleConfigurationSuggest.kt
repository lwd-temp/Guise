package com.houvven.guise.xposed.config.suggest

import com.houvven.guise.xposed.config.ModuleConfigurationConstant
import java.util.Locale
import java.util.TimeZone

/**
 * Suggested options given when the user fills in the configuration
 * @see [ModuleConfigurationSuggestAdapter]
 */
@Suppress("unused")
object ModuleConfigurationSuggest {

    val locale: List<ModuleConfigurationSuggestAdapter<String>> by lazy {
        Locale.getAvailableLocales().map {
            object : ModuleConfigurationSuggestAdapter<String> {
                override val label: String = it.displayName
                override val value: String = it.toString()
            }
        }
    }


    val timezone: List<ModuleConfigurationSuggestAdapter<String>> by lazy {
        TimeZone.getAvailableIDs().map {
            object : ModuleConfigurationSuggestAdapter<String> {
                override val label: String = it
                override val value: String = it
            }
        }
    }

    val timeHookMode: List<ModuleConfigurationSuggestAdapter<ModuleConfigurationConstant.TimeHookMode>> by lazy {
        ModuleConfigurationConstant.TimeHookMode.values().map {
            object : ModuleConfigurationSuggestAdapter<ModuleConfigurationConstant.TimeHookMode> {
                override val label: String = it.name
                override val value: ModuleConfigurationConstant.TimeHookMode = it
            }
        }
    }



}