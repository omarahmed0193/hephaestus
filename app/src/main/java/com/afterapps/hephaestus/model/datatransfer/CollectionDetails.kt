package com.afterapps.hephaestus.model.datatransfer

data class CollectionDetails(
    val artObject: DetailsArtObject
)

data class DetailsArtObject(
    val dating: Dating,
    val longTitle: String,
    val objectNumber: String,
    val plaqueDescriptionEnglish: String,
    val principalMaker: String,
    val title: String,
    val webImage: WebImage
)

data class Dating(
    val presentingDate: String
)