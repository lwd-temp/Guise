package com.houvven.guise.utils.pkg

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmapOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

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