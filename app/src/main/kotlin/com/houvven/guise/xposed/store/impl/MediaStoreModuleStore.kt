package com.houvven.guise.xposed.store.impl

import android.os.Environment
import com.houvven.guise.xposed.config.ModuleConfiguration
import com.houvven.guise.xposed.store.ModuleStore
import timber.log.Timber
import java.io.File

open class MediaStoreModuleStore constructor(
    private val filename: String
) : ModuleStore {

    private val mediaDir by lazy {
        Environment.getExternalStorageDirectory().resolve("Android/media")
    }

    override fun get(packageName: String): ModuleConfiguration? {
        val mediaDirectory = getPackageMediaDirectory(packageName)
        val file = File(mediaDirectory, filename)
        if (!file.exists()) return null
        val jsonStr = file.readText()
        if (jsonStr.isBlank()) return null

        return ModuleConfiguration.fromJsonString(jsonStr)
    }

    override fun set(packageName: String, configuration: ModuleConfiguration) {
        val mediaDirectory = getPackageMediaDirectory(packageName)
        val file = File(mediaDirectory, filename)

        if (file.parentFile?.exists() == false) file.parentFile?.mkdirs()
        kotlin.runCatching {
            if (!file.exists()) file.createNewFile()
        }
        if (!configuration.isEffective()) {
            kotlin.runCatching { file.delete() }.onFailure { Timber.e(it) }
            return
        }
        file.writeText(configuration.toJsonString())
    }

    override fun isConfigured(packageName: String): Boolean {
        val mediaDirectory = getPackageMediaDirectory(packageName)
        val file = File(mediaDirectory, filename)
        return file.exists()
    }

    override fun getConfiguredPackages(): List<String> {
        return mediaDir.listFiles()?.mapNotNull {
            it.run {
                if (exists() && isDirectory) name else null
            }
        } ?: emptyList()
    }

    private fun getPackageMediaDirectory(packageName: String): File {
        return mediaDir.resolve("Android/media/$packageName/")
    }
}