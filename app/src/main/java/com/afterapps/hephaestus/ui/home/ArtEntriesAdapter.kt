package com.afterapps.hephaestus.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.afterapps.hephaestus.databinding.ItemArtCardBinding
import com.afterapps.hephaestus.model.domain.ArtEntry

class ArtEntriesAdapter :
    PagedListAdapter<ArtEntry, ArtEntriesAdapter.ArtEntryViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<ArtEntry>() {
        override fun areItemsTheSame(oldItem: ArtEntry, newItem: ArtEntry): Boolean {
            return oldItem.objectNumber == newItem.objectNumber
        }

        override fun areContentsTheSame(oldItem: ArtEntry, newItem: ArtEntry): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtEntryViewHolder {
        val binding = ItemArtCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtEntryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtEntryViewHolder, position: Int) {
        val artEntry = getItem(position)
        artEntry?.let { holder.bind(it) }
    }

    inner class ArtEntryViewHolder(private val binding: ItemArtCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artEntry: ArtEntry) {
            binding.artEntry = artEntry
            binding.executePendingBindings()
        }
    }
}