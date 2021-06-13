package com.abhi.weather_lookup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.abhi.weather_lookup.dagger.ActivityScope
import com.abhi.weather_lookup.model.WeatherInfo
import com.abhi.weather_lookup.network.Repository
import com.abhi.weather_lookup.util.Resource
import com.abhi.weather_lookup.view.adapters.WeatherListAdapter
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * WeatherListViewModel class to perform business operations for the WeatherInfoActivity
 */
@ActivityScope
class WeatherListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val citySelected = MutableLiveData<String>()
    var weatherInfoClicked = MutableLiveData<RecyclerViewModel>()
    val weathersListAdapter: WeatherListAdapter = WeatherListAdapter()

    fun updateSelectedCity(city: String) {
        citySelected.value = city
    }

    fun updateWeatherInfoClicked(recyclerViewModel: RecyclerViewModel) {
        weatherInfoClicked.value = recyclerViewModel
    }

    fun getWeatherInfo() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = repository.getWeatherInfo(citySelected.value.toString())
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun notifyAdapter(
        weatherInfoList: ArrayList<WeatherInfo>,
        mListener: WeatherListAdapter.IClickListener
    ) {
        weathersListAdapter.updateAdapter(weatherInfoList, mListener)
    }

}