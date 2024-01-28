package com.example.marsestatedata

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marsestatedata.network.MarsProperty
import com.example.marsestatedata.overview.PhotoGridAdapter
import kotlinx.coroutines.newFixedThreadPoolContext

@BindingAdapter("listData")
fun bindRecycleView(recycleView: RecyclerView, data: List<MarsProperty>){
    val adapter = recycleView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

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
