package com.example.marsestatedata.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
This class it`s an API class where we describe how retrofit will fetch data from web-service.
In the end retrofit return a data in a view like string or what we want */

private const val BASE_URL = "https://mars.udacity.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    /*.addConverterFactory(ScalarsConverterFactory.create()) */// Converter for string
    .addConverterFactory(MoshiConverterFactory.create(moshi)) // Converter JSON response from server to Kotlin object
    .baseUrl(BASE_URL).build()

enum class MarsApiFilter(val value: String) {
    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all")
}

interface MarsApiService {
    @GET("realestate")
    suspend fun getProperties( @Query("filter")type: String ): List<MarsProperty> // Do request to server
}

object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}