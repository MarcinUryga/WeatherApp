package com.example.weatherapp.ui.week_weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.WeatherItemBinding
import com.example.weatherapp.ui.week_weather.WeekWeatherViewModel
import com.example.weatherapp.model.local.DayWeather

class WeatherViewHolder private constructor(private val binding: WeatherItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(dayWeather: DayWeather, viewModel: WeekWeatherViewModel) {
        binding.dayWeather = dayWeather
        binding.viewmodel = viewModel
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): WeatherViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = WeatherItemBinding.inflate(layoutInflater, parent, false)
            return WeatherViewHolder(binding)
        }
    }
}