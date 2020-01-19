package com.afterapps.hephaestus

import android.app.Application
import com.afterapps.hephaestus.di.artDetailsModule
import com.afterapps.hephaestus.di.artImageModule
import com.afterapps.hephaestus.di.dataModule
import com.afterapps.hephaestus.di.homeModule
import com.afterapps.hephaestus.worker.UpdateArtEntriesWorker
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        //Initializing koin modules
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, homeModule, artDetailsModule, artImageModule))
        }

        // Initialize update art entries worker
        UpdateArtEntriesWorker.enqueueUpdateArtEntriesWorker(this@App)
    }
}