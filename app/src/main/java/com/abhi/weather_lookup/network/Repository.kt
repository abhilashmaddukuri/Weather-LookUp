package com.abhi.weather_lookup.network

import com.abhi.weather_lookup.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  This repository class is created with the scope of Singleton
 *  i.e., created only once in the App Lifetime, and we can add cache logic here in future
 */
@Singleton
class Repository @Inject constructor(private val apiService: ApiService) {
    suspend fun getWeatherInfo(city: String)
            = apiService.getWeatherInfo(city, Constants.API_KEY, Constants.API_UNITS)
}
