package com.example.weatherapp.ui.week_weather.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.model.local.DayWeather

class DayWeatherDiffUtil : DiffUtil.ItemCallback<DayWeather>() {
    override fun areItemsTheSame(oldItem: DayWeather, newItem: DayWeather): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: DayWeather, newItem: DayWeather): Boolean {
        return oldItem == newItem
    }
}