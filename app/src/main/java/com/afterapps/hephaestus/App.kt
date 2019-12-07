package com.afterapps.hephaestus

import android.app.Application
import com.afterapps.hephaestus.di.dataModule
import com.afterapps.hephaestus.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        //Initializing koin modules
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, homeModule))
        }
    }
}