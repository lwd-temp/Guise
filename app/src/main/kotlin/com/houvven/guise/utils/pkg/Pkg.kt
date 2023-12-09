package com.houvven.guise.utils.pkg

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable

data class Pkg(
    val name: String,
    val packageName: String,
    val icon: Drawable,
    val isSystemPkg: Boolean,
    val versionName: String,
    val versionCode: Int,
    val firstInstallTime: Long,
    val lastUpdateTime: Long
) {
    companion object {
        fun create(
            pm: PackageManager, packageInfo: PackageInfo
        ): Pkg {
            val packageName = packageInfo.packageName
            val appName = packageInfo.applicationInfo.loadLabel(pm).toString()
            val icon = pm.getApplicationIcon(packageName)
            val isSystemPkg = isSystemPkg(packageInfo)
            val versionName = packageInfo.versionName ?: ""
            val versionCode = packageInfo.versionCode
            val firstInstallTime = packageInfo.firstInstallTime
            val lastUpdateTime = packageInfo.lastUpdateTime
            return Pkg(
                name = appName,
                packageName = packageName,
                icon = icon,
                isSystemPkg = isSystemPkg,
                versionName = versionName,
                versionCode = versionCode,
                firstInstallTime = firstInstallTime,
                lastUpdateTime = lastUpdateTime
            )
        }
    }
}

fun isSystemPkg(packageInfo: PackageInfo): Boolean {
    return packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
}