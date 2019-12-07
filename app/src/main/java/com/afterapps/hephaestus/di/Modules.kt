package com.afterapps.hephaestus.di

import androidx.room.Room
import com.afterapps.hephaestus.database.RijksDatabase
import com.afterapps.hephaestus.network.RijksApi
import com.afterapps.hephaestus.repository.RijksRepository
import com.afterapps.hephaestus.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {

    //API
    single { RijksApi().createRijksApiService() }

    //Database
    single {
        Room.databaseBuilder(get(), RijksDatabase::class.java, "rijks-database")
            .build()
    }

    //Rijks repository
    single { RijksRepository(rijksApi = get(), rijksDatabase = get()) }

}

val homeModule = module {

    //Home viewModel
    viewModel { HomeViewModel(rijksRepository = get()) }
}