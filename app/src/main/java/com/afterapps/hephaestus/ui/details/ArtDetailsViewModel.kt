package com.afterapps.hephaestus.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.afterapps.hephaestus.base.BaseViewModel
import com.afterapps.hephaestus.model.domain.ArtEntry
import com.afterapps.hephaestus.network.NetworkStatus
import com.afterapps.hephaestus.repository.RijksRepository
import com.hadilq.liveevent.LiveEvent

class ArtDetailsViewModel(private val rijksRepository: RijksRepository) : BaseViewModel() {

    val artEntryDetails = rijksRepository.artEntryDetails

    private val _artEntry = MutableLiveData<ArtEntry>()
    val artEntry: LiveData<ArtEntry>
        get() = _artEntry

    private val _networkStatus = MutableLiveData<NetworkStatus>()
    val networkStatus: LiveData<NetworkStatus>
        get() = _networkStatus

    private val _navigateToArtImage = LiveEvent<String>()
    val navigateToArtImage: LiveData<String>
        get() = _navigateToArtImage


    // Calls the repository to get art entry details and update the network status
    fun onArtEntryArgsReady(artEntry: ArtEntry) {
        _artEntry.value = artEntry
        _networkStatus.value = NetworkStatus.Loading
        performApiCall(
            call = { rijksRepository.getArtEntryDetails(artEntry.objectNumber) },
            onSuccess = { _networkStatus.postValue(NetworkStatus.Idle) },
            onError = { _networkStatus.postValue(NetworkStatus.Error) }
        )
    }

    fun onArtImageClick(artImageUrl: String) {
        _navigateToArtImage.value = artImageUrl
    }
}