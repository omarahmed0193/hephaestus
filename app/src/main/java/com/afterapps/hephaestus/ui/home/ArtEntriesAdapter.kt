package com.afterapps.hephaestus.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afterapps.hephaestus.R
import com.afterapps.hephaestus.databinding.ItemArtEntryCardBinding
import com.afterapps.hephaestus.model.domain.ArtEntry
import com.afterapps.hephaestus.model.domain.ArtEntryDiffCallback
import com.afterapps.hephaestus.network.NetworkStatus

private const val TYPE_ART = 1
private const val TYPE_PROGRESS = 2

class ArtEntriesAdapter(private val artEntryReactor: ArtEntryReactor) :
    PagedListAdapter<ArtEntry, RecyclerView.ViewHolder>(ArtEntryDiffCallback) {


    private var networkStatus: NetworkStatus? = null

    // True only if the status is new content loading
    private fun hasExtraRow() = networkStatus != null && networkStatus == NetworkStatus.Loading

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_ART -> ArtEntryViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.item_art_entry_card,
                    parent,
                    false
                )
            )
            TYPE_PROGRESS -> ArtEntryProgressViewHolder(
                inflater.inflate(
                    R.layout.item_art_entry_progress,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_ART) {
            val artEntry = getItem(position)
            artEntry?.let { (holder as ArtEntryViewHolder).bind(it) }
        }
    }

    // Adding extra item if there is new content loading
    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            TYPE_PROGRESS
        } else {
            TYPE_ART
        }
    }

    inner class ArtEntryViewHolder(private val binding: ItemArtEntryCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artEntry: ArtEntry) {
            binding.artEntry = artEntry
            binding.artEntryReactor = artEntryReactor
            binding.artEntryImageView = binding.artImageView
            binding.artEntryTitleTextView = binding.artTitleTextView
            binding.executePendingBindings()
        }
    }

    inner class ArtEntryProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // Adds/Removes loading item and notify the adapter
    fun setNetworkState(newNetworkStatus: NetworkStatus?) {
        val previousState = this.networkStatus
        val hadExtraRow = hasExtraRow()
        this.networkStatus = newNetworkStatus
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkStatus) {
            notifyItemChanged(itemCount - 1)
        }
    }
}

// Interface to handle items click
interface ArtEntryReactor {
    fun onArtEntryClick(artEntry: ArtEntry, artImageView: ImageView, artTitleTextView: TextView)
}