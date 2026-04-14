package com.example.tfldepartures.data

import com.google.gson.annotations.SerializedName

data class TflArrival(
    @SerializedName("id")              val id: String,
    @SerializedName("stationName")     val stationName: String,
    @SerializedName("lineName")        val lineName: String,
    @SerializedName("platformName")    val platformName: String,
    @SerializedName("towards")         val towards: String,
    @SerializedName("expectedArrival") val expectedArrival: String,
    @SerializedName("timeToStation")   val timeToStation: Int
)

fun TflArrival.toDeparture() = Departure(
    id, stationName, lineName, platformName,
    towards, expectedArrival, timeToStation
)