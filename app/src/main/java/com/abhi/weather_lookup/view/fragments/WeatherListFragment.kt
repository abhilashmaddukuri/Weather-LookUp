package com.abhi.weather_lookup.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhi.weather_lookup.R
import com.abhi.weather_lookup.databinding.FragmentWeatherListBinding
import com.abhi.weather_lookup.util.Status
import com.abhi.weather_lookup.view.activities.WeatherInfoActivity
import com.abhi.weather_lookup.view.adapters.WeatherListAdapter
import com.abhi.weather_lookup.viewmodel.RecyclerViewModel
import com.abhi.weather_lookup.viewmodel.WeatherListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_weather_list.*
import javax.inject.Inject

/**
 * A WeatherListFragment subclass as the second destination in the navigation.
 */
class WeatherListFragment : Fragment(), WeatherListAdapter.IClickListener {

    @Inject lateinit var weatherListViewModel: WeatherListViewModel

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private var _binding: FragmentWeatherListBinding? = null

    override fun onAttach(context: Context) {
        (activity as WeatherInfoActivity).weatherInfoComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = weatherListViewModel
        updateAppBarTitle()
        setupRecyclerView()
        getWeatherInfoFromAPI()
    }

    private fun getWeatherInfoFromAPI() {
        weatherListViewModel.getWeatherInfo().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    pg_Loader.visibility = View.GONE
                    rv_weather_info.visibility = View.VISIBLE

                    response.data?.list?.let { list ->
                        weatherListViewModel.notifyAdapter(list, this)
                    }
                }
                Status.LOADING -> {
                    pg_Loader.visibility = View.VISIBLE
                    rv_weather_info.visibility = View.GONE
                }
                Status.ERROR -> {
                    pg_Loader.visibility = View.GONE
                    rv_weather_info.visibility = View.GONE

                    Snackbar.make(
                        binding.root, R.string.error_api_response, Snackbar.LENGTH_INDEFINITE
                    ).setAction(getString(R.string.okay)) { activity?.onBackPressed() }.show()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        rv_weather_info.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )

        rv_weather_info.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun updateAppBarTitle() {
        (requireActivity() as WeatherInfoActivity).supportActionBar?.title =
            weatherListViewModel.citySelected.value
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /* On Click of one item from the list */
    override fun itemClickAction(recyclerViewModel: RecyclerViewModel) {
        weatherListViewModel.updateWeatherInfoClicked(recyclerViewModel)
        findNavController().navigate(R.id.action_WeatherListFragment_to_WeatherDetailFragment)
    }
}