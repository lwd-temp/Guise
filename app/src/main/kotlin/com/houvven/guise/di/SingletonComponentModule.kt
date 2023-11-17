package com.houvven.guise.di

import android.content.Context
import com.houvven.guise.utils.pkg.PkgScanner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SingletonComponentModule {

    @Provides
    fun providePkgScanner(@ApplicationContext context: Context): PkgScanner {
        return PkgScanner(context)
    }
}