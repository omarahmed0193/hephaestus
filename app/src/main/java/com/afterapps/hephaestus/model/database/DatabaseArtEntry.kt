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
    val indexInResponse: Int? = -1, //used to sort art entries in database
    val pageNumber: Int? = 0 //next page number for BoundaryCallback
)