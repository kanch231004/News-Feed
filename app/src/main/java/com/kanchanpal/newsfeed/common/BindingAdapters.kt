package com.kanchanpal.newsfeed.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {

    if (!imageUrl.isNullOrEmpty()) {

        GlideApp.with(view.context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .override(DEFAULT_BUFFER_SIZE)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}
