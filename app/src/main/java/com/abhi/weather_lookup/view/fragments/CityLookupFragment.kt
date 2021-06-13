package com.abhi.weather_lookup.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.abhi.weather_lookup.R
import com.abhi.weather_lookup.databinding.FragmentCityLookupBinding
import com.abhi.weather_lookup.view.activities.WeatherInfoActivity
import com.abhi.weather_lookup.viewmodel.WeatherListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_city_lookup.*
import javax.inject.Inject

/**
 * A CityLookupFragment subclass as the default destination in the navigation.
 */
class CityLookupFragment : Fragment() {

    @Inject lateinit var weatherListViewModel: WeatherListViewModel

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private var _binding: FragmentCityLookupBinding? = null

    override fun onAttach(context: Context) {
        // This injects the weatherListViewModel object to this fragment
        (activity as WeatherInfoActivity).weatherInfoComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityLookupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLookup.setOnClickListener {
            if (edt_city.text.toString().isNotEmpty()) {
                weatherListViewModel.updateSelectedCity(edt_city.text.toString())
                findNavController().navigate(R.id.action_CityLookupFragment_to_WeatherListFragment)
            } else {
                Snackbar.make(
                    binding.root, R.string.error_edt_city_validation, Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}