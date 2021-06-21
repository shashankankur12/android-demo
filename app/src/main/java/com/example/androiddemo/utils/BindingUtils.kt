package com.example.androiddemo.utils

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("image")
fun loadImage(view: CircleImageView, url: String) {
    url?.let {  Glide.with(view).load(url).into(view) }
}