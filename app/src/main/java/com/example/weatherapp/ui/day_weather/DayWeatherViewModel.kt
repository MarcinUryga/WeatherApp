package com.example.weatherapp.ui.day_weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.local.DayWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DayWeatherViewModel @Inject constructor() : ViewModel() {
    private val _dayWeather = MutableLiveData<DayWeather>()
    var weather: LiveData<DayWeather> = _dayWeather

    private val _city = MutableLiveData<String>()
    var city: LiveData<String> = _city

    fun updateDayWeather(dayWeather: DayWeather) {
        _dayWeather.value = dayWeather
    }

    fun updateCity(city: String?) {
        city?.let {
            _city.value = it
        }
    }
}