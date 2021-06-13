package com.abhi.weather_lookup.network

import com.abhi.weather_lookup.model.WeatherInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast")
    suspend fun getWeatherInfo(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): WeatherInfoResponse

}