package com.example.jetpackcomposevisualnoteapp.common.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.example.jetpackcomposevisualnoteapp.R

fun ImageView.downloadImage(
    imageUrl: String,
    @DrawableRes errorImage: Int = R.drawable.errorimage
) {
    Glide.with(this.context).load(imageUrl).error(errorImage)
        .into(this)
}