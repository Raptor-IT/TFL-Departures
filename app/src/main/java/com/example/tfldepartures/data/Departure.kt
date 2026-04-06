package com.example.tfldepartures.data

data class Departure(
    val id: String,
    val stationName: String,
    val lineName: String,
    val platformName: String,
    val towards: String,
    val expectedArrival: String,
    val timeToStation: Int
) {
    // Converts seconds into minutes
    val minutesAway: Int get() = timeToStation / 60
}