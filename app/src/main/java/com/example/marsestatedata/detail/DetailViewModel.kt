package com.example.marsestatedata.detail

import android.app.Application
import androidx.constraintlayout.widget.ConstraintSet.Transform
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.marsestatedata.R
import com.example.marsestatedata.network.MarsProperty
import javax.xml.transform.Transformer

class DetailViewModel(marsProperty: MarsProperty, app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<MarsProperty>()
    val selectedProperty: LiveData<MarsProperty>
        get() = _selectedProperty

    init { _selectedProperty.value = marsProperty }

    val displayPropertyPrice = selectedProperty.map {
        app.applicationContext.getString(
            when (it.isRental) {
            true -> R.string.display_price_monthly_rental
            false -> R.string.display_price
        }, it.price)
    }

    val displayPropertyType = selectedProperty.map {
        app.applicationContext.getString(R.string.display_type, app.applicationContext.getString(
            when(it.isRental){
                true -> R.string.type_rent
                false -> R.string.type_sale
        }))
    }






}