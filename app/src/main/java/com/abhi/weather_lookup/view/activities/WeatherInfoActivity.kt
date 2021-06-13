package com.abhi.weather_lookup.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.abhi.weather_lookup.MyApplication
import com.abhi.weather_lookup.R
import com.abhi.weather_lookup.dagger.WeatherInfoComponent
import com.abhi.weather_lookup.databinding.ActivityWeatherInfoBinding
import com.abhi.weather_lookup.viewmodel.WeatherListViewModel
import javax.inject.Inject

class WeatherInfoActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: WeatherListViewModel
    lateinit var weatherInfoComponent: WeatherInfoComponent

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityWeatherInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        initializeDagger()
        super.onCreate(savedInstanceState)
        setUpDataBinding()
        setSupportActionBar(binding.toolbar)
        configureAppBar()
    }

    private fun configureAppBar() {
        val navController = findNavController(R.id.nav_host_fragment_content_weather_info)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setUpDataBinding() {
        binding = ActivityWeatherInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeDagger() {
        // Creation of the login graph using the application graph
        weatherInfoComponent = (applicationContext as MyApplication)
            .appComponent.weatherInfoComponent().create()
        // Make Dagger instantiate @Inject fields in this Activity
        weatherInfoComponent.inject(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_weather_info)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}