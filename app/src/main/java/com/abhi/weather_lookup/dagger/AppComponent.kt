package com.abhi.weather_lookup.dagger

import com.abhi.weather_lookup.network.RetrofitBuilder
import dagger.Component
import javax.inject.Singleton

/* AppComponent defines all the Classes that needs the Dagger injections */
@Singleton
@Component(modules = [RetrofitBuilder::class, SubcomponentsModule::class])
interface AppComponent {
    fun weatherInfoComponent(): WeatherInfoComponent.Factory
}