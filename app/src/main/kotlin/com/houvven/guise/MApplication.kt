package com.houvven.guise

import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MApplication : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}