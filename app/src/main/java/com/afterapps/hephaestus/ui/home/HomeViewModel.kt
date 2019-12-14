package com.afterapps.hephaestus.ui.home

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.afterapps.hephaestus.base.BaseViewModel
import com.afterapps.hephaestus.model.domain.ArtEntry
import com.afterapps.hephaestus.network.NetworkStatus
import com.afterapps.hephaestus.repository.RijksRepository
import com.hadilq.liveevent.LiveEvent

class HomeViewModel(private val rijksRepository: RijksRepository) : BaseViewModel(),
    ArtEntryReactor {

    val artEntries = rijksRepository.artEntries

    val currentPageNumber = rijksRepository.pageNumber

    private val _networkStatus = MutableLiveData<NetworkStatus>()
    val networkStatus: LiveData<NetworkStatus>
        get() = _networkStatus

    private val _navigateToArtEntryDetails = LiveEvent<ArtItemListing>()
    val navigateToArtEntryDetails: LiveData<ArtItemListing>
        get() = _navigateToArtEntryDetails

    // Calls the repository to get new page of data and update the network status
    fun onPageNumberChanged(pageNumber: Int) {
        _networkStatus.value = NetworkStatus.Loading
        performApiCall(
            call = { rijksRepository.getArtEntries(pageNumber) },
            onSuccess = { _networkStatus.postValue(NetworkStatus.Idle) },
            onError = { _networkStatus.postValue(NetworkStatus.Error) }
        )
    }

    // Handle art item click
    override fun onArtEntryClick(
        artEntry: ArtEntry,
        artImageView: ImageView
    ) {
        _navigateToArtEntryDetails.value = ArtItemListing(artEntry, artImageView)
    }
}

data class ArtItemListing(
    val artEntry: ArtEntry,
    val artImageView: ImageView
)

