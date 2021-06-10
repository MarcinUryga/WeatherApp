package com.example.weatherapp.ui.week_weather.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weatherapp.ui.week_weather.WeekWeatherViewModel
import com.example.weatherapp.model.local.DayWeather

class DaysWeatherAdapter(private val viewModel: WeekWeatherViewModel) :
    ListAdapter<DayWeather, WeatherViewHolder>(DayWeatherDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
    }

}