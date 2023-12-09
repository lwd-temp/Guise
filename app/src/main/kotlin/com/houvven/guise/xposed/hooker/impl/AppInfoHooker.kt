package com.houvven.guise.xposed.hooker.impl

import android.content.pm.PackageInfo
import android.os.Build
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.type.android.ApplicationPackageManagerClass
import com.houvven.guise.xposed.config.ModuleConfiguration
import com.houvven.guise.xposed.config.item.AppInfoConf
import com.houvven.guise.xposed.hooker.Hooker

class AppInfoHooker(config: ModuleConfiguration) : Hooker(config) {

    private val conf: AppInfoConf = config.appInfoConf ?: AppInfoConf()

    override fun onHook() {
        if (!conf.isEffective()) return
        this.hookOfPackageManager()
        this.hookOfBuildConfig()
    }


    private fun hookOfPackageManager() {
        ApplicationPackageManagerClass
            .method { name = "getPackageInfo" }
            .giveAll()
            .hookAll()
            .after {
                val packageInfo = result as PackageInfo
                if (packageInfo.packageName != packageName) {
                    YLog.debug("package name [${packageInfo.packageName}] not match, skip.")
                    return@after
                }
                conf.versionName?.let { packageInfo.versionName = it }
                conf.versionCode?.let {
                    packageInfo.versionCode = it
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        packageInfo.longVersionCode = it.toLong()
                    }
                }
            }
    }


    private fun hookOfBuildConfig() {
        val clazz = "$packageName.BuildConfig".toClassOrNull()
        if (clazz == null) {
            YLog.debug("$packageName not found BuildConfig class.")
            return
        }

        clazz.run {
            conf.versionName?.let {
                field { name = "VERSION_NAME"; modifiers { isStatic } }.get().set(it)
            }
            conf.versionCode?.let {
                field { name = "VERSION_CODE"; modifiers { isStatic } }.get().set(it)
            }
        }
    }

}