package com.abhi.weather_lookup

import android.app.Application
import com.abhi.weather_lookup.dagger.AppComponent
import com.abhi.weather_lookup.dagger.DaggerAppComponent

class MyApplication : Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}