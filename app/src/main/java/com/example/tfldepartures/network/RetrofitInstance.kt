package com.example.tfldepartures.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: TflApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.tfl.gov.uk/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TflApiService::class.java)
    }
}


