package com.example.weatherapp.model.local

import org.joda.time.DateTime
import java.io.Serializable

data class DayWeather(
    val date: DateTime,
    val minTemperature: Temperature,
    val maxTemperature: Temperature,
    val dayTemperature: Temperature,
    val weatherIcon: String?,
    val description: String?,
    val pressure: Double?,
    val windSpeed: Double?
): Serializable