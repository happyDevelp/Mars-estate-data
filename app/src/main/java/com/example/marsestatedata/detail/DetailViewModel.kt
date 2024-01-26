package com.example.marsestatedata.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marsestatedata.network.MarsProperty

class DetailViewModel(marsProperty: MarsProperty, app: Application) : AndroidViewModel(app) {

    private val _imgUrl = MutableLiveData<String>()
    val imgUrl: LiveData<String>
        get() = _imgUrl
}