package com.houvven.guise.xposed.utils

import android.content.Context
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.houvven.guise.xposed.store.ModuleStore
import com.houvven.guise.xposed.store.ModuleStoreSingleton


@Suppress("unused")
@JvmOverloads
fun moduleStore(
    context: Context? = null,
    packageParam: PackageParam? = null
): ModuleStore {
    return ModuleStoreSingleton.get(context, packageParam)
}

// @Suppress("unused")
// fun moduleStore(): ModuleStore {
//     return ModuleStoreSingleton.get()
// }