package com.afterapps.hephaestus.pagination

import androidx.paging.PagedList
import com.afterapps.hephaestus.model.domain.ArtEntry
import com.afterapps.hephaestus.repository.RijksRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class RijksBoundaryCallback :
    PagedList.BoundaryCallback<ArtEntry>(), KoinComponent {
    private val rijksRepository: RijksRepository by inject()
    override fun onZeroItemsLoaded() {
        //TODO:Remove this code
        GlobalScope.launch {
            rijksRepository.getArtEntries()
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: ArtEntry) {
        //TODO:Remove this code
        GlobalScope.launch {
            rijksRepository.getArtEntries(itemAtEnd.pageNumber?.plus(1))
        }
    }
}