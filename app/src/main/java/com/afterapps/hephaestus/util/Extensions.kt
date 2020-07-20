package com.afterapps.hephaestus.util

import com.afterapps.hephaestus.model.database.DatabaseArtEntry
import com.afterapps.hephaestus.model.datatransfer.ArtObject
import com.afterapps.hephaestus.model.datatransfer.CollectionDetails
import com.afterapps.hephaestus.model.domain.ArtEntryDetails

//Convert art object response to database art entry
fun ArtObject.asDatabaseObject(pageNumber: Int, indexInResponse: Int): DatabaseArtEntry =
    DatabaseArtEntry(
        objectNumber = objectNumber,
        imgUrl = webImage.url ?: "",
        title = title,
        longTitle = longTitle,
        artRatio = webImage.width.toFloat() / webImage.height.toFloat(),
        indexInResponse = indexInResponse,
        pageNumber = pageNumber
    )


//Convert collection details response to list of art entry details
fun CollectionDetails.asDomainObject() = ArtEntryDetails(
    objectNumber = artObject.objectNumber,
    imgUrl = artObject.webImage.url ?: "",
    title = artObject.title,
    longTitle = artObject.longTitle,
    makerName = artObject.principalMaker,
    description = artObject.label.description,
    presentingDate = artObject.dating.presentingDate
)
