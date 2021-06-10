package com.example.weatherapp.model.local

class WeekWeather(
    val city: String?,
    val avgWeekTemperature: Temperature,
    val days: List<DayWeather>
)