package com.example.marsestatedata.overview

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marsestatedata.R

@BindingAdapter("imageUrl")
    fun bindImage (imgView: ImageView, imgUrl: String?){
        imgUrl?.let {
            val imgUrl = it.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUrl)
                .apply(RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
                )
                .into(imgView)


        }
    }
