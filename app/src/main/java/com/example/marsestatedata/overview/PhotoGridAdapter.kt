package com.example.marsestatedata.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marsestatedata.bindImage
import com.example.marsestatedata.databinding.GridViewItemBinding
import com.example.marsestatedata.network.MarsProperty

class PhotoGridAdapter(private val onClickListener: OnClickListener): androidx.recyclerview.widget.ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder>(DifCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }

        holder.bind(marsProperty)
    }

    class MarsPropertyViewHolder(private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: MarsProperty) {
            bindImage(binding.marsImage, marsProperty.imgSrcUrl)
           /* binding.executePendingBindings()*/
        }
    }

    companion object DifCallBack: DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class OnClickListener(val clickListener: (marsProperty: MarsProperty) -> Unit) {
        fun onClick(marsProperty: MarsProperty) {
            return clickListener(marsProperty)
        }
    }

}


