package com.houvven.guise.xposed.config

import com.houvven.guise.xposed.config.item.AppInfoConf
import com.houvven.guise.xposed.config.item.DatetimeConf
import com.houvven.guise.xposed.config.item.IdentifierConf
import com.houvven.guise.xposed.config.item.LocationConf
import com.houvven.guise.xposed.config.item.PropertiesConf
import com.houvven.guise.xposed.config.item.SimConf
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

private val emptyXposedModuleConfiguration = ModuleConfiguration()


@Serializable
data class ModuleConfiguration(
    /**
     * this value is a identifier, don't need serialize to json
     */
    @Transient
    val packageName: String? = null,

    val propertiesConf: PropertiesConf? = null,
    val simConf: SimConf? = null,
    val locationConf: LocationConf? = null,
    val appInfoConf: AppInfoConf? = null,
    val datetimeConf: DatetimeConf? = null,
    val identifierConf: IdentifierConf? = null,


    val locale: String? = null,

    val gpuVendor: String? = null,
    val gpuRenderer: String? = null,
    val glVersion: String? = null,

    // section display config
    val displayWidth: Int? = null,
    val displayHeight: Int? = null,
    val displayDensity: Int? = null,
    val displayDensityDpi: Int? = null,
    val displayFontScale: Float? = null,

    // section Network info
    // section sim card info



    val wifiMac: String? = null,
    val wifiSSID: String? = null,
    val wifiBSSID: String? = null,
    val bluetoothMac: String? = null,


    val time: Long? = null,
    val timeZone: String? = null,

    val location: LocationConf? = null,

    // section user privacy
    // section blank pass
    val blankPassPhoto: Boolean? = null,
    val blankPassVideo: Boolean? = null,
    val blankPassAudio: Boolean? = null,
    val blankPassContacts: Boolean? = null,
    // section for the device sensor
    val prohibitGyro: Boolean? = null,

    val batteryPercent: Int? = null
) : ModuleConf {

    override fun isEffective(): Boolean {
        return this != emptyXposedModuleConfiguration.copy(packageName = this.packageName)
    }

    fun toJsonString(): String {
        return json.encodeToString(serializer(), this)
    }

    companion object {
        private val json = Json {
            prettyPrint = false
            encodeDefaults = true
            ignoreUnknownKeys = true
        }

        fun fromJsonString(jsonString: String): ModuleConfiguration {
            return json.decodeFromString(serializer(), jsonString)
        }

        fun getEmpty(): ModuleConfiguration {
            return emptyXposedModuleConfiguration
        }
    }
}