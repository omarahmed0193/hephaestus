package com.afterapps.hephaestus.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.afterapps.hephaestus.model.domain.ArtEntry
import com.afterapps.hephaestus.network.NetworkStatus
import com.afterapps.hephaestus.ui.home.ArtEntriesAdapter
import com.bumptech.glide.Glide


//bind image url to image view using glide
@BindingAdapter("imgSrc")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Glide.with(imgView.context)
        .load(imgUrl)
        .into(imgView)
}

//bind art entries data to recycler view adapter
@BindingAdapter("artData")
fun bindRecycler(recyclerView: RecyclerView, data: PagedList<ArtEntry>?) {
    data?.let {
        val adapter = recyclerView.adapter as ArtEntriesAdapter
        adapter.submitList(data)
    }
}

// Bind progress views to loading status
@BindingAdapter("callStatusProgress")
fun bindProgressViewCallStatus(progressView: View, status: NetworkStatus?) {
    progressView.visibility = when (status) {
        NetworkStatus.Loading -> View.VISIBLE
        else -> View.GONE
    }
}

// Bind idle views to idle status
@BindingAdapter("callStatusIdle")
fun bindIdleViewCallStatus(view: View, status: NetworkStatus?) {
    view.visibility = when (status) {
        NetworkStatus.Loading -> View.GONE
        else -> View.VISIBLE
    }
}