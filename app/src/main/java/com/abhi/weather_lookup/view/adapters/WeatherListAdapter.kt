package com.abhi.weather_lookup.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abhi.weather_lookup.R
import com.abhi.weather_lookup.databinding.ItemWeatherBinding
import com.abhi.weather_lookup.model.WeatherInfo
import com.abhi.weather_lookup.viewmodel.RecyclerViewModel

class WeatherListAdapter: RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    private lateinit var mListener: IClickListener
    private lateinit var weatherInfoList: List<WeatherInfo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemWeatherBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_weather,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weatherInfoList[position], mListener)
    }

    override fun getItemCount(): Int {
        return if (::weatherInfoList.isInitialized) weatherInfoList.size else 0
    }

    fun updateAdapter(
        weathersInfoList: ArrayList<WeatherInfo>,
        mListener: IClickListener
    ) {
        this.weatherInfoList = weathersInfoList
        this.mListener = mListener
        this.notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = RecyclerViewModel()

        fun bind(weatherInfo: WeatherInfo, listener: IClickListener) {
            viewModel.bind(weatherInfo)
            binding.itemClick = listener
            binding.viewModel = viewModel
        }
    }

    interface IClickListener {
        fun itemClickAction(recyclerViewModel: RecyclerViewModel)
    }
}