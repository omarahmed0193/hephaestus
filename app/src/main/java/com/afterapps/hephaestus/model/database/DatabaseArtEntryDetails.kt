package com.afterapps.hephaestus.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseArtEntryDetails(
    @PrimaryKey
    val objectNumber: String,
    val imgUrl: String,
    val title: String,
    val longTitle: String,
    val makerName: String,
    val description: String,
    val presentingDate: String
)