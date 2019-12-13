package com.afterapps.hephaestus.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.afterapps.hephaestus.base.BaseViewModel
import com.afterapps.hephaestus.network.NetworkStatus
import com.afterapps.hephaestus.repository.RijksRepository

class HomeViewModel(private val rijksRepository: RijksRepository) : BaseViewModel() {

    val artEntries = rijksRepository.artEntries

    val currentPageNumber = rijksRepository.pageNumber

    private val _networkStatus = MutableLiveData<NetworkStatus>()
    val networkStatus: LiveData<NetworkStatus>
        get() = _networkStatus

    // Calls the repository to get new page of data and update the network status
    fun onPageNumberChanged(pageNumber: Int) {
        _networkStatus.value = NetworkStatus.Loading
        performApiCall(
            call = { rijksRepository.getArtEntries(pageNumber) },
            onSuccess = { _networkStatus.postValue(NetworkStatus.Idle) },
            onError = { _networkStatus.postValue(NetworkStatus.Error) }
        )
    }


}