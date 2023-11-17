package com.houvven.guise.pkg

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmapOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

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
            val versionName = packageInfo.versionName
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

/**
 * Applicable to the pkg class of jetpack compose,
 * the icon type is changed from [Drawable] to [ImageBitmap]
 */
data class PkgWithCompose(
    val name: String,
    val packageName: String,
    val icon: ImageBitmap,
    val isSystemPkg: Boolean,
    val versionName: String,
    val versionCode: Int,
    val firstInstallTime: Long,
    val lastUpdateTime: Long
) {
    companion object {

        private fun withPkg(pkg: Pkg): PkgWithCompose {
            val icon = pkg.icon.toBitmapOrNull()?.asImageBitmap() ?: ImageBitmap(1, 1)
            return PkgWithCompose(
                name = pkg.name,
                packageName = pkg.packageName,
                icon = icon,
                isSystemPkg = pkg.isSystemPkg,
                versionName = pkg.versionName,
                versionCode = pkg.versionCode,
                firstInstallTime = pkg.firstInstallTime,
                lastUpdateTime = pkg.lastUpdateTime
            )
        }

        suspend fun withPkg(pkgs: List<Pkg>) = withContext(Dispatchers.IO) {
            coroutineScope {
                pkgs.map { async { withPkg(it) } }.awaitAll()
            }
        }
    }
}

fun isSystemPkg(packageInfo: PackageInfo): Boolean {
    return packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
}