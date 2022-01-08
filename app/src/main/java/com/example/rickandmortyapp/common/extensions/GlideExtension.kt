package com.example.rickandmortyapp.common.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?, placeHolder: Int = 0) {
    Glide.with(this.context)
        .load(url)
        .error(placeHolder)
        .into(this)
}

