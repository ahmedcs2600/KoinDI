package com.app.koindi.extras

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.app.koindi.global.IMAGE_URL

@BindingAdapter("android:setUri")
fun setUri(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(IMAGE_URL + url).into(imageView)
}