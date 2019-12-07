package com.afterapps.hephaestus.repository

import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.afterapps.hephaestus.database.RijksDatabase
import com.afterapps.hephaestus.model.datatransfer.CollectionsResponse
import com.afterapps.hephaestus.model.domain.ArtEntry
import com.afterapps.hephaestus.network.INITIAL_LOAD_SIZE
import com.afterapps.hephaestus.network.PAGE_SIZE_LIMIT
import com.afterapps.hephaestus.network.RijksApi
import com.afterapps.hephaestus.pagination.RijksBoundaryCallback
import com.afterapps.hephaestus.util.asDatabaseObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RijksRepository(
    private val rijksApi: RijksApi.ApiService,
    private val rijksDatabase: RijksDatabase
) {

    //attach boundaryCallback to database and get art entries
    val artEntries: LiveData<PagedList<ArtEntry>> =
        rijksDatabase.rijksDao.getArtEntries().toLiveData(
            config = Config(
                pageSize = PAGE_SIZE_LIMIT,
                initialLoadSizeHint = INITIAL_LOAD_SIZE,
                prefetchDistance = 0
            ),
            boundaryCallback = RijksBoundaryCallback()
        )

    suspend fun getArtEntries(pageNumber: Int? = 0) {
        withContext(Dispatchers.IO) {
            val collectionsResponse = rijksApi.getCollections(pageNumber)
            insertCollectionsIntoDatabase(collectionsResponse, pageNumber)
        }
    }

    //insert fetched collections into rijks database
    private fun insertCollectionsIntoDatabase(
        collectionsResponse: CollectionsResponse,
        pageNumber: Int?
    ) {
        rijksDatabase.rijksDao.insertArtEntries(
            *collectionsResponse
                .asDatabaseObjects(pageNumber)
                .toTypedArray()
        )
    }

}