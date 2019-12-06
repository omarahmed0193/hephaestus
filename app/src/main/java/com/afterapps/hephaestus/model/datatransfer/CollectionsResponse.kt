package com.afterapps.hephaestus.model.datatransfer

data class CollectionsResponse(
    val artObjects: List<ArtObject>
)

data class ArtObject(
    val longTitle: String,
    val objectNumber: String,
    val title: String,
    val webImage: WebImage
)