package com.afterapps.hephaestus.util

import com.afterapps.hephaestus.model.datatransfer.CollectionDetails
import com.afterapps.hephaestus.model.datatransfer.CollectionsResponse
import com.afterapps.hephaestus.model.domain.ArtEntry
import com.afterapps.hephaestus.model.domain.ArtEntryDetails

//Convert collections response to list of art entries
fun CollectionsResponse.asDomainObjects(): List<ArtEntry> = artObjects.map { artObject ->
    ArtEntry(
        objectNumber = artObject.objectNumber,
        imgUrl = artObject.webImage.url,
        title = artObject.title,
        longTitle = artObject.longTitle
    )
}

//Convert collection details response to list of art entry details
fun CollectionDetails.asDomainObject() = ArtEntryDetails(
    objectNumber = artObject.objectNumber,
    imgUrl = artObject.webImage.url,
    title = artObject.title,
    longTitle = artObject.longTitle,
    makerName = artObject.principalMaker,
    description = artObject.plaqueDescriptionEnglish,
    presentingDate = artObject.dating.presentingDate
)
