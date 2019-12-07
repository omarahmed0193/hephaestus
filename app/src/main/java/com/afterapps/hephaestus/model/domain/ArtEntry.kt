package com.afterapps.hephaestus.model.domain

data class ArtEntry(
    val objectNumber: String,
    val imgUrl: String,
    val title: String,
    val longTitle: String,
    val pageNumber: Int? = 0 //next page number for BoundaryCallback
)