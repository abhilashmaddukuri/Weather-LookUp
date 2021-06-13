package com.abhi.weather_lookup.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.abhi.weather_lookup.R
import com.abhi.weather_lookup.databinding.FragmentWeatherDetailBinding
import com.abhi.weather_lookup.view.activities.WeatherInfoActivity
import com.abhi.weather_lookup.viewmodel.WeatherListViewModel
import javax.inject.Inject

/**
 * A WeatherDetailFragment subclass as the third destination in the navigation.
 */
class WeatherDetailFragment : Fragment() {

    @Inject lateinit var weatherListViewModel: WeatherListViewModel

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private var _binding: FragmentWeatherDetailBinding? = null

    override fun onAttach(context: Context) {
        // This injects the weatherListViewModel object to this fragment
        (activity as WeatherInfoActivity).weatherInfoComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = weatherListViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}