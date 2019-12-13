package com.afterapps.hephaestus.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.afterapps.hephaestus.database.RijksDatabase
import com.afterapps.hephaestus.model.datatransfer.CollectionsResponse
import com.afterapps.hephaestus.model.domain.ArtEntry
import com.afterapps.hephaestus.network.RijksApi
import com.afterapps.hephaestus.pagination.RijksBoundaryCallback
import com.afterapps.hephaestus.util.asDatabaseObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val PAGE_SIZE = 10

private const val PREFETCH_DISTANCE = 0

class RijksRepository(
    private val rijksApi: RijksApi.ApiService,
    private val rijksDatabase: RijksDatabase
) {

    private val _pageNumber = MutableLiveData<Int>()
    val pageNumber: LiveData<Int>
        get() = _pageNumber

    //attach boundaryCallback to database and get art entries
    val artEntries: LiveData<PagedList<ArtEntry>> =
        rijksDatabase.rijksDao.getArtEntries().toLiveData(
            config = Config(
                pageSize = PAGE_SIZE,
                initialLoadSizeHint = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE
            ),
            boundaryCallback = RijksBoundaryCallback()
        )

    suspend fun getArtEntries(pageNumber: Int) {
        withContext(Dispatchers.IO) {
            val collectionsResponse = rijksApi.getCollections(pageNumber)
            insertCollectionsIntoDatabase(collectionsResponse, pageNumber)
        }
    }

    //insert fetched collections into rijks database
    private fun insertCollectionsIntoDatabase(
        collectionsResponse: CollectionsResponse,
        pageNumber: Int
    ) {
        for (artObject in collectionsResponse.artObjects) {

            // Get next index in database to save newly fetched data
            val indexInResponse = rijksDatabase.rijksDao.getNextIndexInResponse()
            rijksDatabase.rijksDao.insertArtEntries(
                artObject.asDatabaseObject(pageNumber, indexInResponse)
            )
        }
    }

    fun onPageNumberChanged(pageNumber: Int) {
        _pageNumber.value = pageNumber
    }

}