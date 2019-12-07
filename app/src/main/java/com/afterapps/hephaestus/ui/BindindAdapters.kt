package com.afterapps.hephaestus.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.afterapps.hephaestus.model.domain.ArtEntry
import com.afterapps.hephaestus.ui.home.ArtEntriesAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


//bind image url to image view using glide
@BindingAdapter("imgSrc")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Glide.with(imgView.context)
        .load(imgUrl)
        .apply(
            RequestOptions()
                .centerCrop()
            //TODO: add {.placeholder().error()} images to the request options
        )
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