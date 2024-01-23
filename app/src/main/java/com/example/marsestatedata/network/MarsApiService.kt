package com.example.marsestatedata.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

/**
 This class it`s an API class where we describe how retrofit will fetch data from web-service.
 In the end retrofit return a data in a view like string or what we want */

private const val BASE_URL = "https://mars.udacity.com/"

private val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).baseUrl(BASE_URL).build()

interface MarsApiService{
 @GET("realestate")
 fun getProperties(): Call<String>
}

object MarsApi{
 val retrofitService: MarsApiService by lazy {
  retrofit.create(MarsApiService::class.java)
 }
}