package com.afterapps.hephaestus.ui.image

import androidx.lifecycle.ViewModel

class ArtImageViewModel() : ViewModel() {

    var artImageUrl: String? = null


    fun onArtImageUrlArgsReady(artImageUrl: String) {
        this.artImageUrl = artImageUrl
    }

}