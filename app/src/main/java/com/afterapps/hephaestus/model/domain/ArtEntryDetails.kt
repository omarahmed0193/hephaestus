package com.afterapps.hephaestus.model.domain

data class ArtEntryDetails(
    val objectNumber: String,
    val imgUrl: String,
    val title: String,
    val longTitle: String,
    val makerName: String,
    val description: String,
    val presentingDate: String
)