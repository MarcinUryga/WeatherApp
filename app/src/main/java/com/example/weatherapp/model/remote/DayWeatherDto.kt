package com.example.weatherapp.model.remote

data class DayWeatherDto(
    val dt: Long,
    val deg: Long,
    val snow: Double? = null,
    val temp: TempDto,
    val speed: Double,
    val clouds: Long,
    val weather: List<WeatherDto>,
    val humidity: Long,
    val pressure: Double
)
