package com.example.marsestatedata.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marsestatedata.network.MarsApi
import com.example.marsestatedata.network.MarsApiFilter
import com.example.marsestatedata.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


enum class MarsApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel() : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    init { getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL) }

    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty?>()
    val navigateToSelectedProperty: LiveData<MarsProperty?>
        get() = _navigateToSelectedProperty


    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _status.value = MarsApiStatus.LOADING
                var listResult = MarsApi.retrofitService.getProperties(filter.value)
                _status.value = MarsApiStatus.DONE
                _properties.value = listResult
            }
            catch (t:Throwable){
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        Job().cancel()
    }

    //trigger
    fun displayPropertyDetails(marsProperty: MarsProperty){
        _navigateToSelectedProperty.value = marsProperty
    }

    //trigger
    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }

    fun updateFilter(filter: MarsApiFilter) {
        getMarsRealEstateProperties(filter)
    }
}