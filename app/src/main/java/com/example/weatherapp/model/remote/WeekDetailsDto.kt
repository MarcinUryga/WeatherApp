package com.example.weatherapp.model.remote

data class WeekDetailsDto(
    val cnt: Long?,
    val cod: String?,
    val city: CityDto?,
    val list: List<DayWeatherDto?>,
    val message: Long?
)