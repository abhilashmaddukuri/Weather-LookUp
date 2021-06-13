package com.abhi.weather_lookup.dagger

import com.abhi.weather_lookup.view.activities.WeatherInfoActivity
import com.abhi.weather_lookup.view.fragments.CityLookupFragment
import com.abhi.weather_lookup.view.fragments.WeatherDetailFragment
import com.abhi.weather_lookup.view.fragments.WeatherListFragment
import dagger.Subcomponent

/* This Subcomponent is used to inject required objects to the scope of finding weather information */
@ActivityScope
@Subcomponent
interface WeatherInfoComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): WeatherInfoComponent
    }

    fun inject(weatherinfoActivity: WeatherInfoActivity)
    fun inject(cityLookupFragment: CityLookupFragment)
    fun inject(weatherListFragment: WeatherListFragment)
    fun inject(weatherDetailFragment: WeatherDetailFragment)
}