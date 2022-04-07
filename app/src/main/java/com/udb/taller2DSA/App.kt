package com.udb.taller2DSA

import android.app.Application
import com.udb.taller2DSA.di.repositoryModule
import com.udb.taller2DSA.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}