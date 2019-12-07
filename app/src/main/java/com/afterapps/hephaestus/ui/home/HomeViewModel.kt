package com.afterapps.hephaestus.ui.home

import androidx.lifecycle.ViewModel
import com.afterapps.hephaestus.repository.RijksRepository

class HomeViewModel(rijksRepository: RijksRepository) : ViewModel() {
    //TODO: implement base view model/fragment
    val artEntries = rijksRepository.artEntries
}