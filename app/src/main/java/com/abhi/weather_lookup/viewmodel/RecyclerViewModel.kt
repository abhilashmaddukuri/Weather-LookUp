package com.abhi.weather_lookup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhi.weather_lookup.model.WeatherInfo

/**
 * Class which holds ViewModel for the Recyclerview adapter
 */
class RecyclerViewModel : ViewModel() {
    private var weatherMain = MutableLiveData<String>()
    private var weatherMainDescription = MutableLiveData<String>()
    private var temp = MutableLiveData<String>()
    private var tempFeelsLike = MutableLiveData<String>()
    private var dateTime = MutableLiveData<String>()

    // Method to bind the data to recyclerview element
    fun bind(weatherInfo: WeatherInfo) {
        weatherMain.value = weatherInfo.weather[0].main
        weatherMainDescription.value = weatherInfo.weather[0].description
        temp.value = weatherInfo.main.temp.toString()
        tempFeelsLike.value = weatherInfo.main.feels_like.toString()
        dateTime.value = weatherInfo.dt_txt
    }

    fun getWeatherMain(): MutableLiveData<String> {
        return weatherMain
    }

    fun getDateTime(): MutableLiveData<String> {
        return dateTime
    }

    fun getWeatherDescription(): MutableLiveData<String> {
        return weatherMainDescription
    }

    fun getTemp(): MutableLiveData<String> {
        return temp
    }

    fun getTempFeelsLike(): MutableLiveData<String> {
        return tempFeelsLike
    }
}