package com.afterapps.hephaestus

import android.app.Application
import com.afterapps.hephaestus.di.dataModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        //Initializing koin modules
        startKoin {
            modules(listOf(dataModule))
        }
    }
}