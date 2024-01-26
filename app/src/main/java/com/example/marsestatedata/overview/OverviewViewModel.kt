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

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _property = MutableLiveData<MarsProperty>()
    val property: LiveData<MarsProperty>
        get() = _property

    init { getMarsRealEstateProperties() }


    private fun getMarsRealEstateProperties() {
        _status.value = "Set the Mars API Response here!"
        CoroutineScope(Dispatchers.Main).launch {

            try {
                var listResult = MarsApi.retrofitService.getProperties()
                _status.value = "Success: ${listResult.size} Mars properties retrieved"
                if (listResult.size > 0) {
                    _property.value = listResult[0]
                }
            }
            catch (t:Throwable){
                _status.value = "Failure: ${t.message}"
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        Job().cancel()
    }
}