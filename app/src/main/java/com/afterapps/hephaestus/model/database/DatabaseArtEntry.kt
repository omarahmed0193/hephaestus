package com.afterapps.hephaestus.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseArtEntry(
    @PrimaryKey
    val objectNumber: String,
    val imgUrl: String,
    val title: String,
    val longTitle: String,
    val artRatio: Float,
    val indexInResponse: Int? = -1, //used to sort art entries in database
    val pageNumber: Int //next page number for BoundaryCallback
)