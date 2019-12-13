package com.afterapps.hephaestus.pagination

import androidx.paging.PagedList
import com.afterapps.hephaestus.model.domain.ArtEntry
import com.afterapps.hephaestus.repository.RijksRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class RijksBoundaryCallback :
    PagedList.BoundaryCallback<ArtEntry>(), KoinComponent {

    // Get rijks repository using koin inject method
    private val rijksRepository: RijksRepository by inject()

    override fun onZeroItemsLoaded() {

        // Set page number to 0 if there's no data in the database
        rijksRepository.onPageNumberChanged(0)
    }

    override fun onItemAtEndLoaded(itemAtEnd: ArtEntry) {

        // Get the next page number
        rijksRepository.onPageNumberChanged(itemAtEnd.pageNumber.plus(1))
    }
}