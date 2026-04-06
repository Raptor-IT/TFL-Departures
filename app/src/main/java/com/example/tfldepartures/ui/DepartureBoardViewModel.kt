package com.example.tfldepartures.ui

import com.example.tfldepartures.data.*
import com.example.tfldepartures.network.RetrofitInstance
import androidx.lifecycle.*
import kotlinx.coroutines.*

class DepartureBoardViewModel : ViewModel() {

    private val _departures = MutableLiveData<List<Departure>>()
    val departures: LiveData<List<Departure>> = _departures

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private var refreshJob: Job? = null

    fun startLiveUpdates(stopId: String, apiKey: String) {
        refreshJob?.cancel()
        refreshJob = viewModelScope.launch {
            while (isActive) {
                fetchDepartures(stopId, apiKey)
                delay(30_000)
            }
        }
    }

    private suspend fun fetchDepartures(stopId: String, apiKey: String) {
        _loading.postValue(true)
        try {
            val arrivals: List<TflArrival> = RetrofitInstance.api.getArrivals(stopId, apiKey)
            val departures: List<Departure> = arrivals.map { arrival: TflArrival -> arrival.toDeparture() }
            val sorted: List<Departure> = departures.sortedBy { departure: Departure -> departure.timeToStation }
            _departures.postValue(sorted)
            _error.postValue(null)
            _departures.postValue(sorted)
            _error.postValue(null)
        } catch (e: Exception) {
            _error.postValue("Failed to load: ${e.message}")
        } finally {
            _loading.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        refreshJob?.cancel()
    }
}


