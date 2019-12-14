package com.afterapps.hephaestus.model.domain

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class ArtEntry(
    val objectNumber: String,
    val imgUrl: String,
    val title: String,
    val longTitle: String,
    val artRatio: Float,
    val pageNumber: Int //next page number for BoundaryCallback
) : Serializable

// DiffCallback for the pager adapter
object ArtEntryDiffCallback : DiffUtil.ItemCallback<ArtEntry>() {
    override fun areItemsTheSame(oldItem: ArtEntry, newItem: ArtEntry): Boolean {
        return oldItem.objectNumber == newItem.objectNumber
    }

    override fun areContentsTheSame(oldItem: ArtEntry, newItem: ArtEntry): Boolean {
        return oldItem == newItem
    }

}