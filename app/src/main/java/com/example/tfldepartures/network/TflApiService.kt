package com.example.tfldepartures.network

import com.example.tfldepartures.data.TflArrival
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TflApiService {
    @GET("StopPoint/{stopId}/Arrivals")
    suspend fun getArrivals(
        @Path("stopId")   stopId: String,
        @Query("app_key") apiKey: String
    ): List<TflArrival>
}