package com.example.marsestatedata.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marsestatedata.network.MarsProperty
import com.google.android.material.progressindicator.DeterminateDrawable

/**
 * Simple ViewModel factory that provides the MarsProperty and context to the ViewModel.
 */
class DetailViewModelFactory (private val marsProperty: MarsProperty, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) return DetailViewModel(marsProperty, application) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}



