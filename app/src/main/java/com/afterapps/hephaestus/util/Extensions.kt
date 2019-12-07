package com.afterapps.hephaestus.util

import com.afterapps.hephaestus.model.database.DatabaseArtEntry
import com.afterapps.hephaestus.model.datatransfer.CollectionDetails
import com.afterapps.hephaestus.model.datatransfer.CollectionsResponse
import com.afterapps.hephaestus.model.domain.ArtEntryDetails

//Convert collections response to list of database art entries
fun CollectionsResponse.asDatabaseObjects(pageNumber: Int?): List<DatabaseArtEntry> =
    artObjects.map { artObject ->
        DatabaseArtEntry(
            objectNumber = artObject.objectNumber,
            imgUrl = artObject.webImage.url,
            title = artObject.title,
            longTitle = artObject.longTitle,
            pageNumber = pageNumber
            //TODO:add index in response
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
