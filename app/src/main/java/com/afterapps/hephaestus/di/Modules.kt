package com.afterapps.hephaestus.di

import com.afterapps.hephaestus.network.RijksApi
import org.koin.dsl.module

val dataModule = module {
    single { RijksApi().createRijksApiService() }
}