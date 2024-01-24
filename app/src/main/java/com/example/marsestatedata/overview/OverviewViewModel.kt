package com.example.marsestatedata.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marsestatedata.network.MarsApi
import com.example.marsestatedata.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel() : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        _response.value = "Set the Mars API Response here!"
        CoroutineScope(Dispatchers.Main).launch {
            try {
                var listResult = MarsApi.retrofitService.getProperties()
                _response.value = "Success ${listResult.size} Mars properties retrieved"
            }
            catch (t:Throwable){
                _response.value = "Failure: ${t.message}"
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        Job().cancel()
    }
}