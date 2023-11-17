package com.houvven.guise.pkg

import android.content.Context
import android.content.pm.PackageManager
import com.houvven.guise.utils.StopWatch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PkgScanner(private val context: Context) {

    private val pm = context.packageManager

    fun scan(completedEveryTimeCallback: () -> Unit = {}): ArrayList<Pkg> {
        val stopwatch = StopWatch()
        stopwatch.start()
        val packages = pm.getInstalledPackages(PackageManager.GET_META_DATA)
        val list = ArrayList<Pkg>()
        packages.mapNotNullTo(list) {
            val pkg = Pkg.create(pm, it)
            completedEveryTimeCallback()
            pkg
        }
        stopwatch.stop { prettyLog() }
        return list
    }

    suspend inline fun suspendScan(noinline completedEveryTimeCallback: () -> Unit = {}): ArrayList<Pkg> {
        return withContext(Dispatchers.IO) {
            scan(completedEveryTimeCallback)
        }
    }

    fun getPkgQuantity(): Int {
        return pm.getInstalledPackages(PackageManager.GET_META_DATA).size
    }
}